package Project1;


public class SecretKeyGuesser {
  public void start() {
    // brute force key guessing
    SecretKey key = new SecretKey();

    String Mstr = "MMMMMMMMMMMM";
    String Ostr = "OOOOOOOOOOOO";
    String Cstr = "CCCCCCCCCCCC";
    String Hstr = "HHHHHHHHHHHH";
    String Astr = "AAAAAAAAAAAA";
    int MFreq = key.guess(Mstr);
    int OFreq = key.guess(Ostr);
    int CFreq = key.guess(Cstr);
    int HFreq = key.guess(Hstr);
    int AFreq = key.guess(Astr);

    System.out.printf("Your string contain: %d M, %d O,%d C,%d H,%d A \n",MFreq,OFreq,CFreq,HFreq,AFreq);

    String str ="MMMMMMMMMMMM";
    for(int i=0;i<OFreq;i++){
      str = guessWithResult(str,'O',key);
    }
    for(int i=0;i<CFreq;i++){
      str = guessWithResult(str,'C',key);
    }
    for(int i=0;i<HFreq;i++){
      str = guessWithResult(str,'H',key);
    }
    for(int i=0;i<AFreq;i++){
      str = guessWithResult(str,'A',key);
    }
        
    System.out.println("I found the secret key. It is " + str); 
  }

  static String guessWithResult(String current,char update,SecretKey key){

    int currentGuess = key.guess(current);
    char[] curr = current.toCharArray();
    char[] result =curr;

    for(int i=curr.length-1;i>=0;i--){
      if(curr [i] == 'M'){
        result[i] = update;
        System.out.println("current guess is: "+String.valueOf(result)+"\n");
        if (key.guess(String.valueOf(result)) > currentGuess){
          return String.valueOf(result);
        }else{
          result[i] = 'M';
        }
        
      }
    }
    return null;
  };




}
