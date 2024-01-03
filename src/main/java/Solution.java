import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public List<String> removeSubfolders(String[] folder) {
        Trie trie = new Trie();
        for(int i = 0; i < folder.length; i++){
            trie.insert(folder[i].split("/"));
        }

        List<String> res = new ArrayList<>();
        serialize(trie.root, res, "");
        return res;
    }

    public void serialize(TrieNode root, List<String> res, String cur){
        if(root.children.isEmpty()){
            res.add(cur);
            return;
        }

        for(Map.Entry<String, TrieNode> e : root.children.entrySet()){
            String s = e.getKey();
            TrieNode next = e.getValue();
            if(cur.length() > 0 && cur.charAt(cur.length() - 1) == '/'){
                serialize(next, res, cur + s);
            }else serialize(next, res, cur + "/" + s);

        }

    }
}

class Trie{
    TrieNode root = new TrieNode();

    public void insert(String[] path){
        TrieNode temp = root;
        int i = 1;
        while(i < path.length && temp.children.containsKey(path[i])){
            if(temp.isTerminal){
                return;
            }
            temp = temp.children.get(path[i]);
            i++;
        }
        if(temp.isTerminal){
            return;
        }
        while(i < path.length){
            temp.children.put(path[i], new TrieNode());
            temp = temp.children.get(path[i]);
            i++;
        }

        temp.isTerminal = true;
        temp.children.clear();
    }




}

class TrieNode{
    Map<String, TrieNode> children = new HashMap<>();
    boolean isTerminal;
}
