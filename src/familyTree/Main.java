package familyTree;

public class Main {

	public static void main(String[] args) {
		/*
		 1
		 |    \
		 1-1     1-2
		 |          |        \
		 1-1-1      1-2-2   1-2-2
		 */
		Member m1 = new Member("1");
		Member m11 = new Member("1-1");
		Member m12 = new Member("1-2");
		Member m111 = new Member("1-1-1");
		Member m121 = new Member("1-2-1");
		Member m122 = new Member("1-2-2");
		
		m11.setFather(m1);
		m12.setFather(m1);
		m111.setFather(m11);
		m121.setFather(m12);
		m122.setFather(m12);
		
		m1.setSon(new Member[]{m11,m12});
		m11.setSon(new Member[]{m111});
		m12.setSon(new Member[]{m121,m122});
		
		System.out.println(m1+"\n"+m11+"\n"+m12+"\n"+m111+"\n"+m121+"\n"+m122);

	}

}
