package model;

import java.util.Timer;
import java.util.TimerTask;


public class Game {
    private int score;
    private Board board;
    private Timer timer;
    private boolean paused;
    private boolean over;


    private Direction direction = Direction.RIGHT;

    /*
      Перечисление с направлениями-векторами
      используются для задачи направления движения змейки.
     */
    public enum Direction {
        UP(-1, 0), DOWN(1, 0), LEFT(0, -1), RIGHT(0, 1);
        private int i;
        private int j;

        Direction(int i, int j) {
            this.i = i;
            this.j = j;
        }

        public int getI() {
            return i;
        }

        public int getJ() {
            return j;
        }

        public boolean isNoOpposite(Direction direction) {
            if ((direction == LEFT | direction == RIGHT) & (this == RIGHT | this == LEFT)) return false;
            else  if ((direction == UP | direction == DOWN) & (this == UP | this == DOWN)) return false;
            return true;
        }
    }

    /*
      Инициализация игры.
      Создаем поле заданного размера и кладем
      в середину поля змейку. В начале
      змейка будет размера 1.
     */
    public Game(int boardWidth, int boardHeight) {
        board = new Board(boardWidth, boardHeight);
        board.putSnake(boardWidth / 2, boardHeight / 2);
        board.generateFood();
        timer = new Timer();
    }

    /*
      Этот метод  задание для таймера, который выполняет его
     (вызывает метод action())
      каждые 100 миллисекунд.
     */
    public void start() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                action();
            }
        }, 0, 100);
    }


    //  Для смены направления движение

    public void turn(Direction direction) {
        if (direction.isNoOpposite(this.direction)) this.direction = direction;
    }

    /*
     Здесь происходит передвижение проверка на столкновение и
     перемещение змейки. Сначала проверяется какая ячейка стоит перед змейков.
     Если это FOOD, то переменная ate принимает значение true и генерируется
     новая еда.
     Далее идет проверка на столкновения с самой змеей и стеной.
     В обоих случаях игра завершается.
     Если смертельных столкновений нет, значит мы перемещаем змею.
     */
    private void action() {
        boolean ate = false;
        Board.Cell nextCell = checkNextCell(); // цел внутри борда
        if (nextCell == Board.Cell.FOOD) {
            ate = true;
            score ++;
            board.generateFood();
        }
        else if (nextCell == Board.Cell.SNAKE) over();
        else if (nextCell == Board.Cell.BARRIER) over();
        board.moveSnake(direction.getI(), direction.getJ(), ate);
    }

    /*
      Метод, проверяющий, что находится в следующей ячейке, в которой
      окажется голова змейки
     */
    private Board.Cell checkNextCell() {
        Board.Cell cell = board.getCell(board.getHead().getI() + direction.getI(),
                board.getHead().getJ() + direction.getJ());
        return cell;
    }

    public void pause() {
        paused = true;
        timer.cancel();
    }

    public void resume() {
        paused = false;
        timer = new Timer();
        start();
    }

    public boolean isPause() {
        return paused;
    }

    public boolean isOver() {return over;}

    public void over() {
        timer.cancel();
        over = true;
    }

    public int getScore() {
        return score;
    }

    public Board getBoard() {
        return board;
    }
}
