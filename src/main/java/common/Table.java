package common;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Table implements Iterable<Table.Row>
{
    private List<Row> rows = new ArrayList<>();
    
    @Override
    public Iterator<Row> iterator()
    {
        return rows.iterator();
    }
    
    public void add(Row row)
    {
        rows.add(row);
    }
    
    public Row get(int index)
    {
        return rows.get(index);
    }
    
    public static class Row
    {
        private final List<String> cols = new ArrayList<>();
        
        public void add(String col)
        {
            cols.add(col);
        }
        
        public String get(int index)
        {
            return cols.get(index);
        }
    }
}
