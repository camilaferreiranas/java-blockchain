/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package noobchain;

import java.util.ArrayList;
import com.google.gson.GsonBuilder;


/**
 *
 * @author cami_
 */
public class Noobchain {

    /**
     * @param args the command line arguments
     */
    
    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    public static int difficulty = 5; 
    public static void main(String[] args) {
        
        blockchain.add(new Block("Olá, sou o primeiro bloco", "0"));
        System.out.println("Tentando minerar o bloco 1");
        blockchain.get(0).mineBlock(difficulty);
        blockchain.add(new Block("Olá, sou o segundo bloco", blockchain.get(blockchain.size()-1).hash));
        System.out.println("Tentando minerar o bloco 2");
         blockchain.get(1).mineBlock(difficulty);
       blockchain.add(new Block("Olá, sou o terceiro bloco", blockchain.get(blockchain.size()-1).hash));
       System.out.println("Tentando minerar o bloco 3");
         blockchain.get(2).mineBlock(difficulty);
         
         System.out.println("\nBlockchain é valido" + isChainValid());
       String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);		
		System.out.println(blockchainJson);
        
        
    }
    
    public static Boolean isChainValid() {
        Block currentBlock; 
        Block previousBlock; 
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');
        for(int i=1; i < blockchain.size(); i ++) {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i-1);
            if(!currentBlock.hash.equals(currentBlock.calculateHash())) {
                System.out.println("A Hash atual não é igual");
                return false;
            }
            
            if(!previousBlock.hash.equals(currentBlock.previousHash)) {
                System.out.println("A Hash anterior não é igual");
                return false; 
            }
            
            if(!currentBlock.hash.substring(0, difficulty).equals(hashTarget)) {
                System.out.println("Esse bloco não foi minerado ainda");
                return false;
            }
        }
        
        return true;
    }
    
}
