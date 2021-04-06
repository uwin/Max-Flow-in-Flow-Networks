package cw;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Flow {
    static int[][] graph;
    static int snodes;
    static int sink;
    static int source;
    static int edges;
    static long start; //starting time
    static ArrayList<String> flow = new ArrayList<String>();

    public static void main (String[] args) throws java.lang.Exception
    {
        Flow m = new Flow();

        Scanner input = new Scanner(System.in);
        int choice=0;
        int nodes=0;

        while(choice!=2) {
            System.out.println("1. Load graphs\n2. exit");
            choice = input.nextInt();
            switch (choice) {
                case 1:
                    m.LoadingOtherGraphs(m,input);
                    m.Display(m);
                    break;
                case 2:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid Input");
                    break;
            }
        }

    }
    void LoadingOtherGraphs(Flow m, Scanner input){
        String number="";
        String filetype="";
        String filename="";
        int count=0;
        int count2=0;
        int col=0;
        edges=0;
        int choice=0;
        System.out.println("1. Bridge\n2. Ladder");
        choice=input.nextInt();
        switch (choice){
            case 1:
                filetype="bridge";
                break;
            case 2:
                filetype="ladder";
                break;
            default:
                System.out.println("Invalid Input");
                break;
        }
        int choiceTwo=0;
        System.out.println("1. "+filetype+"_1"+"\n2. "+filetype+"_2"+"\n3. "+filetype+"_3"+"\n4. "+filetype+"_4"+"\n5. "+filetype+"_5"+"\n6. "+filetype+"_6"+"\n6. "+filetype+"_7"+"\n8. "+filetype+"_8"+"\n9. "+filetype+"_9");
        choiceTwo=input.nextInt();
        switch (choiceTwo){
            case 1:
                filename=filetype+"_1";
                break;
            case 2:
                filename=filetype+"_2";
                break;
            case 3:
                filename=filetype+"_3";
                break;
            case 4:
                filename=filetype+"_4";
                break;
            case 5:
                filename=filetype+"_5";
                break;
            case 6:
                filename=filetype+"_6";
                break;
            case 7:
                filename=filetype+"_7";
                break;
            case 8:
                filename=filetype+"_8";
                break;
            case 9:
                filename=filetype+"_9";
                break;
            default:
                System.out.println("Invalid Input");
                break;
        }

        start=System.currentTimeMillis();
        try {
            File myObj = new File("G:\\My Drive\\iit\\lvl05\\SEM2\\Algo\\CW\\code\\src\\Some input files for testing\\"+filename+".txt");
            Scanner myReader = new Scanner(myObj);
            String data = myReader.nextLine();
            snodes= Integer.parseInt(data.trim());
            count++;

            graph = new int[snodes][snodes];
            if(snodes>0){
                for (int i = 0; i < snodes; i++) {
                    for (int j = 0; j < snodes; j++) {
                        graph[i][j]=0;
                    }
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        try {
            File myObj2 = new File("G:\\My Drive\\iit\\lvl05\\SEM2\\Algo\\CW\\code\\src\\Some input files for testing\\"+filename+".txt");
            Scanner myReader2 = new Scanner(myObj2);
            count=0;
            while (myReader2.hasNextLine()) {
                String data2 = myReader2.nextLine();
                if(count>1){
                    System.out.println("graph = " + Arrays.deepToString(graph));
                }
                count++;
//                    number="";
//                    while(data2.length()!=count2) {
//                        if (data2.substring(count2, count2+1).equals(",")) {
////                            graph[(count-3)][col]=Integer.parseInt(number);
//                            if(Integer.parseInt(number)>0){
//                                edges++;
//                            }
//                            col++;
//                            number="";
//                        }else{
//                            number=number+data2.substring(count2,count2+1);
//                        }
//                        count2++;
//
//                    }
//                }
//                col=0;
//                count2=0;
            }
            myReader2.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    void Display(Flow m){
        int count=0;
        System.out.println("GRAPH VIEW ");
        if(snodes>0){
            for (int i = 0; i < snodes; i++) {
                for (int j = 0; j < snodes; j++) {
                    System.out.print(graph[i][j]+" ");
                }
                System.out.println();
            }
        }
        System.out.println("Edges : "+edges);
        System.out.println("Nodes : "+snodes);
//        System.out.println("Max flow " + m.fordFulkerson(graph, snodes));
        long end=System.currentTimeMillis();
        System.out.println("Time : "+(end-start)+"ms");
        count=flow.size();
        System.out.println("AUGMENTED PATH");
        for(int i=0;i<flow.size();i++){
            count--;
            String val=flow.get(count);
            if(!(val.substring(0,1).equals(String.valueOf(source)))){
                System.out.print(val);
            }else if(i==0){
                System.out.print(val);
            }else{
                System.out.println();
                System.out.print(val);
            }
        }
        System.out.println();
    }
}
