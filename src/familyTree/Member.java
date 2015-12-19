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

	private void setFather(Member Father) {
		this.Father = Father;
	}

	/*
	 * Obsolete Method public void setSon(Member[] Son) { this.Son = Son; }
	 */

	public void addSon(Member Son) {
		if (this.Son == null) {
			this.Son = new Member[] { Son };
		} else {
			Member temp[] = this.Son;
			this.Son = new Member[temp.length + 1];
			int i = 0;
			for (Member o : temp) {
				this.Son[i] = o;
				i++;
			}
			this.Son[i] = Son;
		}
		Son.setFather(this);
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
