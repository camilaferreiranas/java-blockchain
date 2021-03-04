/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package noobchain;

import java.util.Date;
import utils.StringUtil;

/**
 *
 * @author cami_
 */
public class Block {
    
    public String hash; 
    public String previousHash;
    private String data; 
    private long timeStamp; 
    private int nonce; 

    public Block(String data,String previousHash) {
        
        this.previousHash = previousHash;
        this.data = data;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }
    
    public String calculateHash() {
    String calculatedhash = StringUtil.applySha256(previousHash + Long.toString(timeStamp) + data);
    return calculatedhash;
    }
    
    public void mineBlock(int difficulty) {
    String target = new String(new char[difficulty]).replace('\0', '0');
    while(!hash.substring( 0, difficulty).equals(target)) {
    nonce++;
    hash = calculateHash();
    }
        System.out.println("Bloco minerado!!!!!!!"+ hash);
    }
    
    
}
