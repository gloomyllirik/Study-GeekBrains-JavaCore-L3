import java.util.ArrayList;
import java.util.Iterator;

public class Box<T extends Fruit> {
    private ArrayList<T> fruitArraylist;
    private float weight;
    private float weightCapacity;
    private String typeFruit;

    public Box() {
        this.weight = 0;
        this.weightCapacity = 10;
        this.typeFruit = Fruit.class.getSimpleName();
        fruitArraylist = new ArrayList<T>();
    }

    public Box(float weightCapacity) {
        this.weight = 0;
        this.weightCapacity = weightCapacity;
        this.typeFruit = Fruit.class.getSimpleName();
        fruitArraylist = new ArrayList<T>();
    }

    public Box(float weightCapacity, String typeFruit) {
        this.weight = 0;
        this.weightCapacity = weightCapacity;
        this.typeFruit = typeFruit;
        fruitArraylist = new ArrayList<T>();
    }

    public float getWeight(){

        return weight;
    }

    public boolean compare(Box compareBox){
        boolean result;

        if (this.weight != compareBox.getWeight())
            result = false;
        else
            result = true;

        return result;
    }

    // Метод, который позволяет пересыпать фрукты из текущей коробки в другую (помним про сортировку
    // фруктов: нельзя яблоки высыпать в коробку с апельсинами). Соответственно, в текущей коробке фруктов
    // не остается, а в другую перекидываются объекты, которые были в этой коробке
    public boolean pour(Box pourBox){
        boolean result = false;

        if ((this.typeFruit.equals(Fruit.class.getSimpleName()) || this.typeFruit.equals(pourBox.getType())) &&
                this.weight <= (pourBox.getWeightCapacity() - pourBox.getWeight())){
            Iterator<T> iterator = fruitArraylist.iterator();
            while (iterator.hasNext()){
                pourBox.put(iterator.next());
            }
            fruitArraylist.clear();
            weight = 0;
            result = true;
        }

        return result;
    }

    public void put(T fruit){
        fruitArraylist.add(fruit);
        weight += fruit.weight;
        if (typeFruit.equals(Fruit.class.getSimpleName()))
            typeFruit = fruit.getClass().getSimpleName();
    }

    public String getType(){
        return this.typeFruit;
    }

    public float getWeightCapacity() {
        return weightCapacity;
    }

    public void info(){
        System.out.println("Коробка под фрукты типа: " + typeFruit);
        System.out.println("Вместимость коробки: " + weightCapacity);
        System.out.println("Текущий вес коробки: " + weight + "\n");
    }

}