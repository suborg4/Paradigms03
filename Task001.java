/**
*Крестики-нолики
 * ● Контекст
* Вероятнее всего, вы с детства знакомы с этой игрой. Пришло
* время реализовать её. Два игрока по очереди ставят крестики
* и нолики на игровое поле. Игра завершается когда кто-то
* победил, либо наступила ничья, либо игроки отказались
* играть.
* ● Задача
* Написать игру в “Крестики-нолики”. Можете использовать
* любые парадигмы, которые посчитаете наиболее
* подходящими. Можете реализовать доску как угодно - как
* одномерный массив или двумерный массив (массив массивов).
* Можете использовать как правила, так и хардкод, на своё
* усмотрение. Главное, чтобы в игру можно было поиграть через
* терминал с вашего компьютера.
*
* Императивный стиль используется для управления ходом игры и изменения состояния игрового поля.
* Процедурный стиль помогает организовать код, разбивая его на логические блоки с помощью функций.
* В коде есть магические числа и нет возможности начать новую игру без перезапуска программы)
*/

import java.util.Scanner;

public class Task001 {
    static char[] maps = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
    static int[][] victories = {
        {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
        {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
        {0, 4, 8}, {2, 4, 6}
    };

    public static void main(String[] args) {
        boolean finish = false;
        boolean player1 = true;
        int count = 0;
        printMaps();

        while (!finish && count < 9) {
            char symbol = player1 ? 'X' : 'O';
            int step = getPlayerInput(symbol);
            
            if (step < 1 || step > 9 || maps[step - 1] == 'X' || maps[step - 1] == 'O') {
                System.out.println("Вы ввели некорректное значение. Попробуйте снова.");
                continue;
            }

            stepMaps(step, symbol);
            printMaps();

            char winner = getResult();
            if (winner != ' ') {
                finish = true;
                if (winner == 'X' || winner == 'O') {
                    System.out.println("Игра окончена! Победил " + winner + "!!!");
                } else {
                    System.out.println("Игра окончена. Ничья!!!");
                }
            } else {
                player1 = !player1;
                count++;
            }
        }
    }

    static void printMaps() {
        for (int i = 0; i < 9; i++) {
            System.out.print(maps[i] + " ");
            if ((i + 1) % 3 == 0) {
                System.out.println();
            }
        }
    }

    static void stepMaps(int step, char symbol) {
        maps[step - 1] = symbol;
    }

    static char getResult() {
        char win = ' ';
        for (int[] victory : victories) {
            if (maps[victory[0]] == 'X' && maps[victory[1]] == 'X' && maps[victory[2]] == 'X') {
                win = 'X';
            } else if (maps[victory[0]] == 'O' && maps[victory[1]] == 'O' && maps[victory[2]] == 'O') {
                win = 'O';
            }
        }
        if (win == ' ' && !new String(maps).contains("123456789")) {
            win = 'T'; // Ничья
        }
        return win;
    }

    static int getPlayerInput(char symbol) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ходит '" + symbol + "': ");
        return scanner.nextInt();
    }
}