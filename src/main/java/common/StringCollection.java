package common;

public class StringCollection
{
    private StringCollection()
    {
        
    }
    
    public static class XPath
    {
        private XPath()
        {
            
        }
        
        public static final String ANY_ELEMENT = "//*";
    }
    
    public static class Identifier
    {
        private Identifier()
        {
            
        }
        
        public static final String EXPECTED_VALUE = "Expected value: ";
        public static final String ACTUAL_VALUE = "Actual value: ";
        public static final String CONTAINMENT_VALUE = "Containment value: ";
    }
    
    public static class Error
    {
        private Error()
        {
            
        }
        
        public static final String TIMEOUT_HEADER = "Timeout: ";
        public static final String UNEXPECTED_TAG_NAME_HEADER = "Unexpected tag name: ";
        public static final String NO_SUCH_ELEMENT_HEADER = "No such element: ";
        public static final String NOT_CONTAINS_HEADER = "Does not contain: ";
    }
}
