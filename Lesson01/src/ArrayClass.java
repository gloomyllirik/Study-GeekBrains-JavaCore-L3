import java.util.ArrayList;

public class ArrayClass<T> {

    private T array[];
    ArrayList<T> list;

    public ArrayClass(T[] array) {
        this.array = array;
        this.list = new ArrayList();
    }

    public void replaceArray(int x, int y){
        T buffer;

        buffer = this.array[x];
        this.array[x] = this.array[y];
        this.array[y] = buffer;
    }

    public void convertArray(){
        list.clear();
        for (int i=0; i < this.array.length; i++){
            list.add(array[i]);
        }
        list.trimToSize();
    }

    public void printArray(){
        System.out.println(String.format("Массив %s array[%s]: ", this.array.getClass().getSimpleName(), this.array.length));
        for (int i=0; i < this.array.length; i++){
            System.out.print(this.array[i] + " ");
        }
        System.out.println();
    }

    public void printList(){
        System.out.println("Коллекция ArrayList: " + list);
    }
}
