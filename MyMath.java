package myApp;

class MyMath {
    //Gets random number
    int getNum(int low, int high){
        return (int)(low + Math.random() * high); //[low, high>
    }

    //Gets operator
    String getOperator(boolean[] list){
        String[] operations = {"+", "-", "*", "/"};
        String[] newOperations = new String[operations.length];
        
        for(boolean b: list){
            if (!b){
                return "1"; //This means error
            }
            else{
                break;
            }
        }

        for(int i=0; i<list.length; i++){
            if (list[i] == true){
                newOperations[i] = operations[i];
            }
            else if (list[i] == false){
                newOperations[i] = "NULL";
            }
        }

        int ranNum = (int)(Math.random() * newOperations.length); // [low, high>
        while(newOperations[ranNum] == "NULL"){
            ranNum = (int)(Math.random() * newOperations.length); // [low, high>
        }
        return newOperations[ranNum];
    }


    //Rounds numbers
    double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
    
        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    //Calculates if the input is correct
    boolean utregn(int tall1, int tall2, double resultat, String operasjon){
        switch(operasjon){
            case("+"):
                if ( (tall1 + tall2) == resultat )
                    return true;
                else
                    return false;
            case("-"):
                if ( (tall1 - tall2) == resultat )
                    return true;
                else
                    return false;
            case("*"):
                if ( (tall1 * tall2) == resultat )
                    return true;
                else
                    return false;
            case("/"):
                if ( round((double)tall1 / (double)tall2, 2) == resultat )
                    return true;
                else
                    return false;
        }
        return false;
    }
}
