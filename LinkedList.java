public class LinkedList  {
    /*Node root; //это ссылка на самую первую ноду*/

    /*LinkedList () {
        root = new Node(); //новая главная нода, с которой все начинается
        root.value = 2;//записываем какое-нибудь значение

        Node node2 = new Node(); //следующая нода
        node2.value = 4;//записываем какое-нибудь значение
        root.next = node2;

        Node node3 = new Node(); //следующая нода
        node3.value = 4;//записываем какое-нибудь значение
        node2.next = node3;
    }*/
    private Node root; //основная нода
    private int size;//сщздаем размер, его менять нельзя, только если создаем или удаляем
    public void add(int value) {//ф-ция добавления
        if (root == null) {// это если список полностью пустой
            root = new Node(value);
            size = 1;
            return;
        }
        //если список не пустой, то нужно по нему пройтись
        Node currentNode = root;
        while (currentNode.next != null)
            currentNode = currentNode.next;//здесь остановились на последней ноде
        currentNode.next = new Node(value);//записали в ноду значение, которое она отдает
        size++;

    }
    public void addSorted(int value){//добавление значений с сортировкой
        //т.е. ищем значение, которое больше, если некст больше, то вставляем
        if (root == null){
            root = new Node(value);
            size = 1;
            return;
        }
        Node addedNode = new Node(value);//добавленная нода
        if(root.value > value){
            addedNode.next = root;
            root = addedNode;
            size++;
            return;

        }
        Node currentNode = root;
        while (currentNode.next != null) {
            if (currentNode.next.value > value) {
                addedNode.next = currentNode.next;
                currentNode.next = addedNode;
                size++;
                return;

            }
            currentNode = currentNode.next;

        }
        currentNode.next = addedNode;

    }

    public void print() {// метод печати списка
        Node currentNode = root;
        System.out.print("[");
        while (currentNode != null) {
            System.out.print(currentNode.value + " ");
            currentNode = currentNode.next;
        }
        System.out.print("]  size: " + size);
        System.out.println();

    }
    public  boolean  remove(int value){//удаление по значению
        if (root == null)
            return  false;
        if (root.value == value) {
            root = root.next; //т.е. вместо нулевой ноды подставляем первую
            size--;
            return  true;
        }
        Node currentNode = root;//создаем переменную, чтобы бежать по списку
        while (currentNode.next !=null) {
            if (currentNode.next.value == value) {
                currentNode.next = currentNode.next.next;//теряем указатель на само значение
                size--;
                return true;
            }
            currentNode = currentNode.next;
        }
        return  false;

    }
    public  boolean removeAt(int index) {//удаление по индексу
        if (index < 0 || index >= size)
            return  false;
        if (index == 0) {
            root = root.next;
            size--;
            return true;
        }
        Node prev = getNode(index - 1);
        if (prev == null)
            return  false;
        prev.next = prev.next.next;
        size--;
        return  true;

    }
    public int removeAll(int value) {
        if (root == null)
            return -1;
        int counter = size;
        while (root.next != null && root.value == value) {// цикл проверяет значение рута до тех пор, пока он не станет велью
            this.print();
            root = root.next;
            size--;

            /*while (remove(value)){ альтернативный вариант, только проходить каждый раз будет заново*/
        }
        Node currentNode = root;
        while (currentNode.next != null) {
            if (currentNode.next.value == value) {
                currentNode.next = currentNode.next.next;
                size--;
                continue;//чтобы не перескочить на следующую ноду
            }
            currentNode = currentNode.next;
        }
        return counter -size;
    }
    public  void quickSort() {//метод сортировки, создаем сразу два, публичный м приватный
        quickSort(0,size -1);
    }
    private void quickSort(int leftBorder, int rightBorder) {//метод сортировки
        int leftMarker = leftBorder;
        int rightMarker = rightBorder;
        int pivot = this.getValue((leftMarker + rightMarker) / 2);
        while (leftMarker <= rightMarker) {
            while (this.getValue(leftMarker) < pivot)
                leftMarker++;
            while (this.getValue(rightMarker) > pivot)
                rightMarker--;
            if (leftMarker <= rightMarker) {
                if (leftMarker < rightMarker)
                    swap(leftMarker,rightMarker);
                leftMarker++;
                rightMarker--;
            }
        }
        if (leftMarker < rightBorder)
            quickSort(leftMarker, rightBorder);
        if (leftBorder < rightMarker)
            quickSort(leftBorder,rightMarker);

    }

    private  Node getNode(int index) { //получить номер определенной ноды
        if (index < 0 || index >= size )
            return null;
        Node currentNode = root;
        for (int i = 0; i < index; i++)
            currentNode = currentNode.next;
        return currentNode;
    }
    public int getValue(int index) {
        if (index < 0 || index >= size )
            throw new ArrayIndexOutOfBoundsException();//выкидываем ошибку от пользователя
        Node currentNode = root;
        for (int i = 0; i < index; i++)
            currentNode = currentNode.next;
        return currentNode.value;

    }
    public void swap(int index1, int index2){//смена значений местами
        if (index1 < 0 || index1 <= size || index2 < 0 || index2 <= size)
            return;
        Node node1 = null, node2 = null, currentNode = root;//создаем две ноды
        for (int i = 0; i < size; i++) {
            if (node1 == null)
                if (index1 == i)
                    node1 = currentNode;
            if (node2 == null)
                if (index2 == i)
                    node2 = currentNode;
            if (node2 != null && node1 != null)
                break;
            currentNode = currentNode.next;
        }
        int temp = node1.value;
        node1.value = node2.value;
        node2.value = temp;


    }

    public int size() { //метод получения пользователем размера
        return size;
    }



    private static class Node {
        int value;
        Node next;


        public Node (){}
        public Node (int _value) {
            this.value = _value;
        } //стандартный конструктор
        public Node (int _value,Node _next) {
            this.value = _value;
            this.next = _next;
        } //стандартный конструктор

    }
    public void revert(Node currentNode, Node previousNode){

            if (currentNode.next == null){
                root = currentNode;
            }
            else {
                revert(currentNode.next,currentNode);
            }
            currentNode.next = previousNode;
            previousNode.next = null;
    }
}



