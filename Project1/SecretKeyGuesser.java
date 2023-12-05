package Project1;


public class SecretKeyGuesser {

  String Ostr = "OOOOOOOOOOOO";
  String Cstr = "CCCCCCCCCCCC";
  String Hstr = "HHHHHHHHHHHH";
  String Astr = "AAAAAAAAAAAA";
  String str =  "MMMMMMMMMMMM";
  public void start() {
    // brute force key guessing
    SecretKey key = new SecretKey();


    int OFreq = key.guess(Ostr);
    int CFreq = key.guess(Cstr);
    int HFreq = key.guess(Hstr);
    int AFreq = key.guess(Astr);

    int currentScore=12-OFreq-CFreq-HFreq-AFreq;
    System.out.printf("Your string contain: %d M, %d O,%d C,%d H,%d A \n",12-OFreq-CFreq-HFreq-AFreq,OFreq,CFreq,HFreq,AFreq);

    
    for(int i=0;i<OFreq;i++){
      currentScore = guessWithResult(currentScore,'O',key);
    }
    for(int i=0;i<CFreq;i++){
      currentScore = guessWithResult(currentScore,'C',key);
    }
    for(int i=0;i<HFreq;i++){
      currentScore = guessWithResult(currentScore,'H',key);
    }
    for(int i=0;i<AFreq;i++){
      currentScore = guessWithResult(currentScore,'A',key);
    }
        
    System.out.println("I found the secret key. It is " + str); 
  }

  int guessWithResult(int score,char update,SecretKey key){

    char[] curr = str.toCharArray();
    char[] result =curr;

    for(int i=curr.length-1;i>=0;i--){
      if(curr [i] == 'M'){
        result[i] = update;
        System.out.println("current guess is: "+String.valueOf(result)+"\n");
        if (key.guess(String.valueOf(result)) > score){
          score++;
          str = String.valueOf(result);
          return score;
        }else{
          result[i] = 'M';
        }
        
      }
    }
    return -1;
  };
}
