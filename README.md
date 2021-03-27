## Генерация байткода

Программа для динамического создания байткода `.class` файла при помощи библиотеки [ASM](https://asm.ow2.io/) на примере простой игры Guess Number.

Запуск осуществляется при помощи `gradle`:
```
$ gradle run
```
Результатом работы является сгенерированный `.class` файл с названием `GuessNumber.class` в корне проекта.

Пример запуска:
```
$ java -cp . GuessNumber
I've thought a number, try to guess!
Enter a guess:
50
Lower.
Enter a guess:
75
Greater.
Enter a guess:
60
Lower.
Enter a guess:
70
Greater.
Enter a guess:
65
Lower.
Enter a guess:
68
Your guess is correct. Congratulations!
```
