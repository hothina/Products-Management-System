package model;

import java.io.Serializable;
import java.util.Date;
import java.util.TreeMap;

public class Order implements Serializable {
    private long id;
public TreeMap<String ,Integer> treeOrder;
    private Date createdAt;
    private long total;
    private long totalMoney;

}
