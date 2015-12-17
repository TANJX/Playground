package familyTree;

public class Member {
	private String name;
	private Member Father;
	private Member Son[];

	public Member(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setFather(Member Father) {
		this.Father = Father;
	}

	public void setSon(Member[] Son) {
		this.Son = Son;
	}

	public String toString() {
		StringBuffer temp = new StringBuffer("My name is " + name);
		if (Son != null) {
			temp.append(", I have " + Son.length + " son(s):");
			for (Member o : Son) {
				temp.append(" " + o.getName());
			}
		}
		if (Father != null)
			temp.append(", my father is " + Father.getName());
		temp.append(".");
		return (temp.toString());
	}

}
