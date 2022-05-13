package subdictionary;

import java.util.NoSuchElementException;

/*
    Louis-Charles Marquis 40177137
    Vlad Tita 40209853
    COMP249
    Assignment # 4, Part 2.3
    April 15th, 2022
*/

public class CellList {
	
    CellNode head; //holds the reference to the first node in the list
    int size;//size of the list; if empty, size==null

    //creates an empty list
    public CellList() {
        head=null;
        size=0;
    }
    
    //copy constructor
    CellList(CellList c){
        head=c.head;
        size=c.size;
    }
    
    //returns the number of nodes in the list
    public int size(){
        int count=0;
        //sets the position at the first node (head node)
        CellNode position = head;
        while(position!=null){
            count++;
            //points to the next node
            position=position.pointer;
        }
        return count;
    }
    
        //----------------------------------------------------------------------
        //Inner class
        private class CellNode{
            private CellPhone cellPhone;//node's data
            private CellNode pointer;//pointer to the next cellNode object

            public CellNode() {
                cellPhone=null;
                pointer=null;
            }

            public CellNode(CellPhone cellPhone, CellNode pointer) {
                this.cellPhone=cellPhone;
                this.pointer=pointer;
            }
            
            //copy constructor
            public CellNode(CellNode c){
                cellPhone=c.cellPhone;
                pointer=c.pointer;
            }
            
            //clone method
            public Object clone() throws CloneNotSupportedException {
                return super.clone();
            }
            
            public CellPhone getCellPhone() {
                return cellPhone;
            }
            
            //use head.getPointer() to get the refference of the first node in the list
            public CellNode getPointer() {
                return pointer;
            }

            public void setCellPhone(CellPhone cellPhone) {
                this.cellPhone = cellPhone;
            }

            public void setPointer(CellNode pointer) {
                this.pointer = pointer;
            }
        }
    
    //inserts a node at the start of the linked list    
    public void addToStart(CellPhone c){
        //creates a new node and places it at the start of the list
        head=new CellNode(c, head);
        this.size++;
    }
    
    //inserts a node at a certain index of the linked list
    public void insertAtIndex(CellPhone c, int index){
        try{
            if(index<0 || index>this.size){
                throw new NoSuchElementException();
            }
            //create the node to be inserted
            CellNode nodeToInsert = new CellNode(c,null);
            //check if list is empty
            if(head==null){
                //insert node as head
                head = nodeToInsert;
            }
            else{
                CellNode n = head;//temp node starting from beginning
                CellNode position = head.pointer;//temp node starting at 2nd node
                int count=0;//keep track of where we are at in the linked list
                while(n.pointer!=null){//go through all elements of linked list
                    if(count==index){//if matching index
                        n.pointer=nodeToInsert;//inserts the given node by pointing the previous node to it
                        nodeToInsert.pointer=position;//the given node points to the previous next node
                        this.size++;
                        break;
                    }
                    n=n.pointer;//points to next node, increment node by 1
                    position=position.pointer;//points to next node, increment node by 1
                    count++;//increment count
                }
            }
        }
        catch(NoSuchElementException e){
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }
    
    //deletes a node at given index
    public void deleteFromIndex(int index){
        if(index<0 || index>this.size || head==null){
                throw new NoSuchElementException();
        }
        
        CellNode temp = head;//first node
        CellNode temp2 = head.pointer;//second node
        int count=0;
        while(temp.pointer!=null){
            if(count==index){
                temp.pointer=temp2.pointer;//deletes temp2 node from the list
                this.size--;
            }
            temp=temp.pointer;//points to the next node
            temp2=temp2.pointer;//points to the next node
            count++;
        }
    }
    
    //deletes the first node of the linked list
    public void deleteFromStart(){
        if(head!=null){
            return;
        }
        else if(size==1){
            head=null;
            this.size--;
        }
        else{
            //make the head equal to the pointer pointing to the next node
            //thus, the head becomes the next node
            head=head.pointer;
            this.size--;
        }
    }
    
    //replaces a node at the given index with another node
    public void replaceAtIndex(CellPhone c, int index){
        if(index<0 || index>this.size || head==null){
                throw new NoSuchElementException();
        }
        
        CellNode temp = head;//first node
        CellNode temp2 = head.pointer;//second node
        CellNode replace = new CellNode(c,null);
        int count=0;
        while(temp.pointer!=null){
            if(count==index){
                temp.pointer=replace;//replaces temp2 node from the list
                replace.pointer=temp2.pointer;//points to the next node
                //size stays the same
            }
            temp=temp.pointer;//points to the next node
            temp2=temp2.pointer;//points to the next node
            count++;
        }
    }
    
    //find the node containing the given serial number
    public CellNode find(long serialNum){
        CellNode position=head;//get the starting node
        //variables to store data
        CellPhone cellPhone;
        long serialNumber;
        while(position!=null){//go through the whole linked list
            cellPhone=position.getCellPhone();//get the cellPhone node
            serialNumber=cellPhone.getSerialNum();//get the serial number of the cell phone
            if(serialNumber==serialNum){//check if serial number corresponds
                return position;//returns index 
            }
            position=position.getPointer();
        }
        return null;
    }
    
    //checks if the linked list contains a specific serial number
    public boolean contains(long serialNum){
        return(find(serialNum)!=null);
    }
    
    //prints a formatted version of all the nodes in the linked list
    public void showsContent(){
        //message presenting the linked list
        System.out.println("The current size of the list is " + size + ". Here are the content of the list");
        System.out.println("==========================================================================");
        //loop through and print all the items in the linked list
        CellNode position = head; 
        CellPhone cellphone;
        String brand;//needs to be 16 characters, fill with white space at the end
        while(position!=null){//go through all the link list
            cellphone = position.getCellPhone();//get the cellphone from the specific node
            brand = cellphone.getBrand();
            //fill the rest of the string with white space until 16 characters are reached
            String paddedBrand = String.format("%-16s", brand);//left aligned, 16 char string filled with white space
            System.out.println("["+cellphone.getSerialNum()+" "+paddedBrand+""+
                    cellphone.getPrice()+" "+cellphone.getYear());
        }
    }
    
    public boolean equals(CellList obj){
        // if lists aren't of same size, return false
        if (size!=this.size) {
            return false;
        }

        //check if objects are of same class
        if (obj.getClass()!=getClass()) {
            return false;
        }

        //Linked list passed as an argument in parameter
        CellNode position = head;//get position of first node for 1st list
        CellNode otherPosition = obj.head;//get position of first node for 2nd list
        //go through first list
        while(position!=null){
            CellPhone cellphone = position.getCellPhone();
            CellPhone otherCellphone = position.getCellPhone();
            if(cellphone.getBrand() != otherCellphone.getBrand()){
                return false;
            }
            else if(cellphone.getBrand() != otherCellphone.getBrand()){
                return false;
            }
            else if(cellphone.getBrand() != otherCellphone.getBrand()){
                return false;
            }
            position=position.getPointer();
            otherPosition=otherPosition.getPointer();
        }
        return true;
    }
}