package ru.job4j.condition;

/**
 * @author Viacheslav Solomatov (solomatoff.vyacheslav@yandex.ru)
 * @version $Id$
 * @since 0.1
 */

public class DummyBot {
    /**
     * Отвечает на вопросы.
     * @param question Вопрос от клиента.
     * @return Ответ.
     */
    public String answer(String question) {
        String rsl = "Это ставит меня в тупик. Спросите другой вопрос.";
        if ("Привет, Бот.".equals(question)) {
            // Если пришел вопрос "Привет, умник."
            rsl = "Привет, умник.";
        } else if ("Пока.".equals(question)) {
            // Если пришел вопрос "Пока.".
            rsl = "До скорой встречи.";
        }
        return rsl;
    }
}