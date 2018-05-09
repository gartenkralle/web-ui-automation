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
    
    public static class Row implements Iterable<Row.Cell>
    {
        private final List<Cell> cells = new ArrayList<>();
        
        @Override
        public Iterator<Row.Cell> iterator()
        {
            return cells.iterator();
        }
        
        public void add(Cell cell)
        {
            cells.add(cell);
        }
        
        public static class Cell
        {
            private String cell;
            
            public String get()
            {
                return cell;
            }
            
            public void set(String cell)
            {
                this.cell = cell;
            }
        }
    }
}
