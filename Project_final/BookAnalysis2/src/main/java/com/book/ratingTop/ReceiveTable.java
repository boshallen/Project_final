package com.book.ratingTop;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapred.lib.db.DBWritable;

import org.apache.hadoop.io.Text;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ReceiveTable implements Writable,DBWritable{
	  //column1:keyword  column2:number
    private String keyWord;
    private float number;

    public ReceiveTable(){

    }
//    public ReceiveTable(String keyWord,int number){
//        this.keyWord = keyWord;
//        this.number = number;
//    }
    public ReceiveTable(String keyWord,Float number){
        this.keyWord = keyWord;
        this.number = number;
    }
    /**Writable  only serializable and deseiralizable
     *
     * @param out
     * @throws IOException
     */
    public void write(DataOutput out) throws IOException {
        out.writeFloat(this.number);
        /*1.将this.keyWord以UTF8的编码方式写入到out中[Write a UTF8 encoded string to out]
        2.其实这个效果和out.writeInt(this.number)是一样的，只不过是DataOutput类型没有writeString()这个方法，
        所以借用了Text.writeString(...)这个方法
         */
        Text.writeString(out, this.keyWord);
    }

    public void readFields(DataInput in) throws IOException {
        this.number = in.readFloat();
        this.keyWord = in.readUTF();
    }


    /**DBWritable
     * write data to mysql
     * @param statement
     * @throws SQLException
     */
    public void write(PreparedStatement statement) throws SQLException {
        statement.setString(1,this.keyWord);
        statement.setFloat(2,this.number);
    }

    /**DBWritable
     * get data from resultset.And set in your fields
     * @param resultSet
     * @throws SQLException
     */
    public void readFields(ResultSet resultSet) throws SQLException {
        this.keyWord = resultSet.getString(1);
        this.number = resultSet.getInt(2);
    }

}
