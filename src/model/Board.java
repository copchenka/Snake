package model;

import java.util.ArrayDeque;
import java.util.Deque;

/*
  Класс - игровое поле.
  Поле реализовано как сетка (матрица), в которую
  помещаются змейка, еда и барьеры.
  Каждое поле матрицы имеет тип - Cell.
  Пустая ячейча имеет значение - null,
  тело змейки - SNAKE, барьер(стена) - BARRIER,
  еда - FOOD.

  Имеется один конструктор, который принимает ширину и высоту.
  Он инициализирует матрицу "cells". После инициализации
  матрица "cells" заполнена null-ами.

  Змейка выполнена в виде очереди, что позволяет лучше
  манипулировать ей.
 */

public class Board {
    private Cell[][] cells;
    private int rows;
    private int columns;
    private Deque<Point> snake; //ссылка на интерф хранит поинты

    public enum Cell {
        SNAKE, FOOD, BARRIER
    }

    //Создаем матрицу-поле.  width - ширина матрицы, height - высота
    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        cells = new Cell[rows][columns];
        snake = new ArrayDeque<>();


        /*
          создание стен вокруг поля
         */
        for (int i = 0; i < rows; i++) {
            cells[i][0] = Cell.BARRIER;
            cells[i][columns - 1] = Cell.BARRIER;
        }

        for (int i = 1; i < columns - 1; i++) {
            cells[0][i] = Cell.BARRIER;
            cells[rows - 1][i] = Cell.BARRIER;
        }
    }

    // начальное положение змейки
    public void putSnake(int row, int column) {
        snake.add(new Point(row, column));
        cells[row][column] = Cell.SNAKE;
    }

    /*
      Перемещение змейки. Получаем координаты напрвления-вектора.
      Прибавляем их к координатам головы змеки. Если змейка ничего не съела,
      то удаляем хвост из очереди, а так же из матрицы-поля.

      Далее обновляем поле, добавляя туда новые координаты змейки.
     */
    public void moveSnake(int i, int j, boolean ate) {
        snake.add(new Point(getHead().getI() + i, getHead().getJ() + j));
        if (!ate) {
            Point tail = snake.poll();
            cells[tail.getI()][tail.getJ()] = null;
        }
        for (Point p : snake) {
            cells[p.getI()][p.getJ()] = Cell.SNAKE;
        }
    }

    /**
     * Генерация еды.
     * Генерируем два случайных числа. Если их координаты
     * совпадут с координатами змейки или границ, то вызываем метод заново
     */
    public void generateFood() {
        int randomI = (int) (Math.random() * rows);
        int randomJ = (int) (Math.random() * columns);

        if (cells[randomI][randomJ] != null) {
            generateFood();
        } else {
            cells[randomI][randomJ] = Cell.FOOD;
        }
    }

    public Point getHead() {
        return snake.peekLast();
    }

    public Cell getCell(int i, int j) {
        return cells[i][j];
    }

    public Cell[][] getBoard() {
        return cells;
    }
}
