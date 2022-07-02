package com.phil.blockchain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BlockUtils {
    private List<Block> blockChain = new ArrayList<>();

    public List<Block> getBlockChain() {
        return blockChain;
    }

    public void addBlock(String data) {
        Block previousBlock = getLatestBlock();
        Block newBlock = new Block(previousBlock.getIndex() + 1, data, previousBlock.getHash());
        blockChain.add(newBlock);
    }

    public boolean isChainValid() {
        for (int i = 1; i < blockChain.size(); i++) {
            Block currentBlock = blockChain.get(i);
            Block previousBlock = blockChain.get(i - 1);
            if (!Objects.equals(currentBlock.getHash(), currentBlock.calculateHash())) {
                return false;
            }
            if (!Objects.equals(currentBlock.getPreviousHash(), previousBlock.getHash())) {
                return false;
            }
        }
        return true;
    }

    private void createGenesisBlock() {
        blockChain.add(new Block(0, "0", "Hello"));
    }

    private Block getLatestBlock() {
        if (blockChain.isEmpty()) {
            createGenesisBlock();
        }
        return blockChain.get(blockChain.size() - 1);
    }
}
