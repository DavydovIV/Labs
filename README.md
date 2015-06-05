# Labs
#Лабораторные работы по курсу Администрирование суперкомпьютеров
#Преподаватель: Лукашин А.А.
#Студент: Давыдов И.В.
# Гр. 53607\1 СПБГПУ Телематика

Задание №1

1. Получение интерфейса и задачи в redmine;
2. Реализация заданного интерфейса коллекции - создание класса, который реализует методы.
3. Добавление поддержки обобщенных типов в коллекцию;
4. Реализация интерфейса Iterable, для итерации по элементам коллекции в стиле foreach;
5. Написание отчета в wiki.

/**
 * Коллекция хранит данные в структуре Red-black tree и гарантирует 
 * логарифмическое время вставки, удаления и поиска.
 * 
 * 
 */
public interface IRedBlackTree {
  /**
   * Добавить элемент в дерево
   * @param e
   */
  void add(Comparable e);
  /**
   * Удалить элемент из дерева
   */
  boolean remove(Comparable o);
  /**
   * Возвращает true, если элемент содержится в дереве
   */
  boolean contains(Comparable o);
}
