package lab1;

/**
 * Коллекция хранит данные в структуре Red-black tree и гарантирует
 * логарифмическое время вставки, удаления и поиска.
 *
 *
 */
public interface IRedBlackTree<Key extends Comparable<Key>> {
    /**
     * Добавить элемент в дерево
     * @param e
     */
    void add(Key e);
    /**
     * Удалить элемент из дерева
     */
    boolean remove(Key o);
    /**
     * Возвращает true, если элемент содержится в дереве
     */
    boolean contains(Key o);
}

