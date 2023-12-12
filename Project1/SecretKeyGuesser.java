package Project1;

public class SecretKeyGuesser {

  String Ostr = null;
  String Cstr = null;
  String Hstr = null;
  String Astr = null;
  String Mstr =  null;
  SecretKey key = null;
  final int KEY_LENGTH = 12;
  int currentScore;

  public SecretKeyGuesser(){
    Ostr = "O".repeat(KEY_LENGTH);
    Cstr = "C".repeat(KEY_LENGTH);
    Hstr = "H".repeat(KEY_LENGTH);
    Astr = "A".repeat(KEY_LENGTH);
    Mstr =  "M".repeat(KEY_LENGTH);
  }

  public void start() {
    // brute force key guessing
    key = new SecretKey();

    int OFreq = key.guess(Ostr);
    int CFreq = key.guess(Cstr);
    int HFreq = key.guess(Hstr);
    int AFreq = key.guess(Astr);
    int MFreq = KEY_LENGTH - OFreq - CFreq - HFreq - AFreq;
    
    System.out.printf("Your string contain: %d M, %d O,%d C,%d H,%d A \n", MFreq, OFreq, CFreq, HFreq, AFreq);
    
    currentScore = MFreq;
    for(int i=0; i<OFreq; i++){
      guessWithResult('O');
    }

    for(int i=0; i<CFreq; i++){
      guessWithResult('C');
    }

    for(int i=0; i<HFreq; i++){
      guessWithResult('H');
    }

    for(int i=0; i<AFreq; i++){
      guessWithResult('A');
    }
    
    if (currentScore == KEY_LENGTH)
      System.out.println("I found the secret key. It is " + Mstr); 
    else
      System.out.println("Current Score: " + currentScore); 
  }

  void guessWithResult(char update){

    char[] MstrArray = Mstr.toCharArray();

    for(int i=MstrArray.length-1; i>=0; i--){
      if(MstrArray [i] == 'M'){
        MstrArray[i] = update;
        System.out.println("current guess is: " + String.valueOf(MstrArray) + "\n");
        if (key.guess(String.valueOf(MstrArray)) > currentScore){
          currentScore ++;
          Mstr = String.valueOf(MstrArray);
          return;
        } else {
          MstrArray[i] = 'M';
        }
      }
    }
  };
}
