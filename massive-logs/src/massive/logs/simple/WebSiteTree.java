import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class WebSiteTree {
	
	private static void parseLine(TreeNode root, String path, int count) {

		TreeNode currentNode = root;

		String[] parts = path.split("/");

		for (String part : parts) {

			TreeNode newNode = new TreeNode(part, count);

			if (currentNode.addChild(newNode)) {
				currentNode = newNode;
			} else {

				Iterator<TreeNode> it = currentNode.iterator();
				while (it.hasNext()) {
					TreeNode treeNode = it.next();
					if (treeNode.equals(newNode)) {
						treeNode.setCount(treeNode.getCount()
								+ count);
						currentNode = treeNode;
						break;
					}
				}
			}

		}
	}

	public static TreeNode makeStatsFromOutputFile(String fileName){
		TreeNode root = new TreeNode("");
		
		BufferedReader readbuffer;
		try {
			readbuffer = new BufferedReader(new FileReader(fileName));
			String strRead = null;
			while ((strRead = readbuffer.readLine()) != null) {
				String splitarray[] = strRead.split("\\s+");
				parseLine(root, splitarray[0], Integer.parseInt(splitarray[1]));
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return root;
	}

	public static TreeNode makeStatsFromMap(Map<String, Integer> stats){
		
		TreeNode root = new TreeNode("");
		
		for (Map.Entry<String, Integer> entry : stats.entrySet())
			parseLine(root, entry.getKey(), entry.getValue());
		
		
		return root;
		
	}


}

class TreeNode implements Iterable<TreeNode> {

	private Set<TreeNode> children;

	private String value;

	private int count = 0;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getCount() {
		return count;
	}

	public int setCount(int count) {
		return this.count = count;
	}

	public TreeNode(String value) {
		this();
		this.value = value;
	}
	

	public TreeNode(String value, int count) {
		this();
		this.value = value;
		this.count = count;
	}

	public TreeNode() {
		children = new HashSet<TreeNode>();
	}

	public boolean addChild(TreeNode n) {
		return children.add(n);
	}

	public boolean removeChild(TreeNode n) {
		return children.remove(n);
	}

	public Iterator<TreeNode> iterator() {
		return children.iterator();
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		return this.value.equals(((TreeNode) arg0).getValue());
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public void showTree(int depth){
		showTree(this,0, depth);
	}
	
	private void showTree(TreeNode t, int n, int depth){
		
		if (n < depth) {

			Iterator<TreeNode> it = t.iterator();
			while (it.hasNext()) {
				TreeNode node = it.next();
				for (int i = 0; i < n; i++)
					System.out.print(" ");
				System.out.println(node.getValue() + "(" + node.getCount()
						+ ")");
				showTree(node, n + 1, depth);
			}
		}
	}

}