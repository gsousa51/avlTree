import java.util.Random;

class CreateHugeTree {
	static void main(String argv[]){
	Random random= new Random();
	int j= 0;
	for(j=0;j<4;j++)
	{
		for (int i = 0; i < 100; i++) {
			System.out.println(j + " insert " + random.nextInt(500));
			System.out.println(j + " inOrder");
			System.out.println(j + " preOrder");
		}
	}
	for(j=0;j<4;j++)
	{
		for (int i = 0; i < 100; i++) {
			System.out.println(j + " delete " + random.nextInt(500));
			System.out.println(j + " inOrder");
			System.out.println(j + " preOrder");
		}
	}
	
	for(j=0;j<4;j++)
	{
		for (int i = 0; i < 100; i++) {
			System.out.println(j + " search " + random.nextInt(500));
			System.out.println(j + " inOrder");
			System.out.println(j + " preOrder");
		}
	}
}
}
