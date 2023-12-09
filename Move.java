import java.util.ArrayList;

// class to store single object name
class SingleObject{
    private final String itemName;
    public SingleObject(String itemName){
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }

}
// Box class that stores box number , number of items and the list of items
class Box{
    private int noOfItems;
    private final int boxNumber;
    private ArrayList<Object> items;

    public Box(int noOfItems , int boxNumber){
        this.noOfItems=noOfItems;
        this.boxNumber=boxNumber;
        items=new ArrayList<>(this.noOfItems);
    }

    public void addItem(Object item) {
        items.add(item);
    }

    public void print(){
        for (Object item : items) {
            if (item.getClass().getSimpleName().equals("SingleObject")) {
                System.out.println(((SingleObject) item).getItemName());
            } else if (item.getClass().getSimpleName().equals("Box")) {
                ((Box) item).print();
            }
        }
    }

    public int getBoxNumber() {
        return boxNumber;
    }

    public int find(String itemName) {
        for (Object item : items) {
            if (item.getClass().getSimpleName().equals("SingleObject")) {
                SingleObject obj1 = ((SingleObject) item);
                if (obj1.getItemName().equals(itemName)) {
                    return boxNumber;
                }
            } else if (item.getClass().getSimpleName().equals("Box")) {
                int result = ((Box) item).find(itemName);
                if (result != -1) {
                    return result;
                }
            }
        }
        return -1;
    }

}
class Move {
    private ArrayList<Box> boxes;

    public Move(int noOfBoxes){
        this.boxes = new ArrayList<>(noOfBoxes);
    }

    public void addBox(Box box){
        boxes.add(box);
    }
    public void print(){
        System.out.println("The objects of my Move are:");
        for(Box box : boxes){
            box.print();
        }
    }

    public int find(String itemName) {
        for (Box box : boxes) {
            int result = box.find(itemName);
            if (result != -1) {
                return box.getBoxNumber();
            }
        }
        return -1;
    }



    public static void main(String[] args) {
        // We create a move that will hold 2 main boxes
        Move move = new Move(2);

        /*
         * We create and then fill 3 boxes
         * Arguments of the constructor of Box:
         * argument 1: number of items (simple items/objects or box) that the box can hold
         * argument 2: box number
         */

        // box 1 contains scissors
        Box box1 = new Box(1, 1);
        box1.addItem(new SingleObject("scissors"));

        // box 2 contains one book
        Box box2 = new Box(1, 2);
        box2.addItem(new SingleObject("book"));

        // box 3 contains one compass
        // and one box containing one scarf
        Box box3 = new Box(2, 3);
        box3.addItem(new SingleObject("compass"));
        Box box4 = new Box(1, 4);
        box4.addItem(new SingleObject("scarf"));
        box3.addItem(box4);

        // We add the three boxes to the first box of move - see below
        Box box5 = new Box(3, 5);
        box5.addItem(box1);
        box5.addItem(box2);
        box5.addItem(box3);

        // We add one box containing 3 objects to move
        Box box6 = new Box(3, 6);
        box6.addItem(new SingleObject("pencils"));
        box6.addItem(new SingleObject("pens"));
        box6.addItem(new SingleObject("rubber"));

        // We add the two most external boxes to the move
        move.addBox(box5);
        move.addBox(box6);

        // We print all the contents of the move
        move.print();

        // We print the number of the outermost cardboard containing the item "scarf"
        System.out.println("The sarf is in the cardboard number " + move.find("scarf"));
    }
}