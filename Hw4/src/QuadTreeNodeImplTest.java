import static org.junit.Assert.*;
import org.junit.Test;


public class QuadTreeNodeImplTest {

    //test BuiltIntArray
    
    @Test
    public void buildFromNegativeVals() {
        int[][] testArray = {{-1, -2, 3, 8},
                             {-2, -4, 9, 9},
                             {2, 2, 3, 3},
                             {2, 2, 1, 1}};
        
        QuadTreeNode testNode = QuadTreeNodeImpl.buildFromIntArray(testArray);
        
        QuadTreeNodeImpl nodeImpl = (QuadTreeNodeImpl) testNode;


        assertFalse(nodeImpl.leftTop.isLeaf());
        assertTrue(nodeImpl.leftBottom.isLeaf());
        assertFalse(nodeImpl.rightTop.isLeaf());
        assertFalse(nodeImpl.rightBottom.isLeaf());
        
        
    }
    
    @Test
    public void testBuiltIntArrayIsNull() {
       
        int[][] arr = 
        {{1, 5},
            {1, 1}};

        QuadTreeNode returned = QuadTreeNodeImpl.buildFromIntArray(arr);
        QuadTreeNodeImpl nodeImpl = (QuadTreeNodeImpl) returned;
        assertNull(nodeImpl.color);
    }
    
    @Test
    public void testBuiltIntArrayIsTopLeftLeafColor() {
        int[][] arr = 
        {{5, 1},
         {1, 1}};

        QuadTreeNode returned = QuadTreeNodeImpl.buildFromIntArray(arr);
        QuadTreeNodeImpl nodeImpl = (QuadTreeNodeImpl) returned;
        assertEquals(5, (int) nodeImpl.leftTop.color);
    }
    
    
    @Test
    public void testBuiltIntArrayIsBottomLeftLeafColor() {
        int[][] arr = 
        {{5, 1},
         {1, 6}};

        QuadTreeNode returned = QuadTreeNodeImpl.buildFromIntArray(arr);
        QuadTreeNodeImpl nodeImpl = (QuadTreeNodeImpl) returned;
        assertEquals(1, (int) nodeImpl.leftBottom.color);
    }
    
    @Test
    public void testBuiltIntArrayIsBottomRightLeafColor() {
        int[][] arr = 
        {{5, 9},
         {1, 6}};

        QuadTreeNode returned = QuadTreeNodeImpl.buildFromIntArray(arr);
        QuadTreeNodeImpl nodeImpl = (QuadTreeNodeImpl) returned;
        assertEquals(6, (int) nodeImpl.rightBottom.color);
    }
    
    @Test
    public void testBuiltIntArrayIsTopRightLeafColor() {
        int[][] arr = 
        {{5, 9},
         {1, 6}};
        QuadTreeNode returned = QuadTreeNodeImpl.buildFromIntArray(arr);
        QuadTreeNodeImpl nodeImpl = (QuadTreeNodeImpl) returned;
        assertEquals(9, (int) nodeImpl.rightTop.color);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNotPowerOfTwo() {
        int[][] arr = 
            {{5, 1, 4},
             {1, 1, 6},
             {1, 1, 6}};
        QuadTreeNodeImpl.buildFromIntArray(arr);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testNotPerfectSquare() {
        int[][] arr = 
            {{5, 1},
             {1, 1},
             
             {1, 1}};
        QuadTreeNodeImpl.buildFromIntArray(arr);
    }
    
    
    @Test(expected = IllegalArgumentException.class)
    public void testEmptyInputArray() {
        int[][] arr = 
            {};
        QuadTreeNodeImpl.buildFromIntArray(arr);
    }
    
    
    @Test(expected = IllegalArgumentException.class)
    public void testNullInputArray() {
        int[][] arr = null;
        QuadTreeNodeImpl.buildFromIntArray(arr);
    }
    
    //test isPowerOf2 Helper Function
    
    //size tests
    @Test
    public void testGetSmallSize() {
        int[][] arr = 
        {{5, 1},
         {1, 1}};

        QuadTreeNode returned = QuadTreeNodeImpl.buildFromIntArray(arr);
        assertEquals(5, returned.getSize());
    }
    
    @Test
    public void testGetLargeSize() {
        int[][] arr = 
        {{1, 1, 5, 6},
         {1, 1, 5, 5},
         {8, 1, 6, 6},
         {1, 1, 6, 6}};

        QuadTreeNode returned = QuadTreeNodeImpl.buildFromIntArray(arr);
        assertEquals(13, returned.getSize());
    }
    
    @Test
    public void f4X4GetSize() {
        int[][] arr = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}};
        
        QuadTreeNode returned = QuadTreeNodeImpl.buildFromIntArray(arr);
        assertEquals(21, returned.getSize());
    }
    
    @Test
    public void testGetMegaSizeOne() {
        int[][] arr = 
        {{6, 6, 6, 6, 6, 6, 6, 6},
         {6, 6, 6, 6, 6, 6, 6, 6},
         {6, 6, 6, 6, 6, 6, 6, 6},
         {6, 6, 6, 6, 6, 6, 6, 6},
         {6, 6, 6, 6, 6, 6, 6, 6},
         {6, 6, 6, 6, 6, 6, 6, 6},
         {6, 6, 6, 6, 6, 6, 6, 6},
         {6, 6, 6, 6, 6, 6, 6, 6}};

        QuadTreeNode returned = QuadTreeNodeImpl.buildFromIntArray(arr);
        assertEquals(1, returned.getSize());
    }
    
    
    @Test
    public void testGetMegaSizeTwo() {
        int[][] arr = 
        {{8, 6, 6, 6, 6, 6, 6, 6},
         {6, 6, 6, 6, 6, 6, 6, 6},
         {6, 6, 6, 6, 6, 6, 6, 6},
         {6, 6, 6, 6, 6, 6, 6, 6},
         {6, 6, 6, 6, 6, 6, 6, 6},
         {6, 6, 6, 6, 6, 6, 6, 6},
         {6, 6, 6, 6, 6, 6, 6, 6},
         {6, 6, 6, 6, 6, 6, 6, 6}};

        QuadTreeNode returned = QuadTreeNodeImpl.buildFromIntArray(arr);
        assertEquals(13, returned.getSize());
    }
    
    
    @Test
    public void testGetMediumSizeOne() {
        int[][] arr = 
        {{1, 2, 6, 6},
         {6, 6, 6, 6},
         {6, 6, 6, 6},
         {6, 6, 6, 6}};

        QuadTreeNode returned = QuadTreeNodeImpl.buildFromIntArray(arr);
        assertEquals(9, returned.getSize());
    }
    

    //get dimension tests
    @Test
    public void testGetDimension() {
        int[][] arr = 
        {{5, 1},
         {1, 1}};

        QuadTreeNode returned = QuadTreeNodeImpl.buildFromIntArray(arr);
        assertEquals(2, returned.getDimension());
    }
    
    @Test
    public void testGetMegaDimension() {
        int[][] arr = 
        {{6, 6, 6, 6, 6, 6, 6, 6},
         {6, 6, 6, 6, 6, 6, 6, 6},
         {6, 6, 6, 6, 6, 6, 6, 6},
         {6, 6, 6, 6, 6, 6, 6, 6},
         {6, 6, 6, 6, 6, 6, 6, 6},
         {6, 6, 6, 6, 6, 6, 6, 6},
         {6, 6, 6, 6, 6, 6, 6, 6},
         {6, 6, 6, 6, 6, 6, 6, 6}};

        QuadTreeNode returned = QuadTreeNodeImpl.buildFromIntArray(arr);
        assertEquals(8, returned.getDimension());
    }
    
    @Test
    public void testMegaLarge() {
        int[][] arr = 
            {{5, 5, 5, 5, 6, 6, 6, 6},
             {5, 5, 5, 5, 6, 6, 6, 6},
             {5, 5, 5, 5, 6, 6, 6, 6},
             {5, 5, 5, 5, 6, 6, 6, 6},
             {4, 4, 4, 4, 0, 0, 5, 5},
             {4, 4, 4, 4, 0, 0, 5, 5},
             {4, 4, 4, 4, 5, 5, 5, 5},
             {4, 4, 4, 4, 5, 5, 5, 5}};
    
        QuadTreeNode returned = QuadTreeNodeImpl.buildFromIntArray(arr);
        assertEquals(8, returned.getDimension());
    }
    
    @Test
    public void testGetMegaDimension2() {
        int[][] arr = 
        {{5, 5, 5, 5},
         {5, 5, 5, 5},
         {5, 5, 6, 6},
         {5, 5, 6, 6}};

        QuadTreeNode returned = QuadTreeNodeImpl.buildFromIntArray(arr);
        assertEquals(4, returned.getDimension());
    }
    
    @Test
    public void testGetDimension2() {
        int[][] arr = 
        {{1, 1},
         {1, 1}};

        QuadTreeNode returned = QuadTreeNodeImpl.buildFromIntArray(arr);
        assertEquals(2, returned.getDimension());
    }
    
    //isLeaf Test
    @Test
    public void testIsLeaf() {
        int[][] arr = 
        {{1, 1},
         {1, 1}};
       
        QuadTreeNode returned = QuadTreeNodeImpl.buildFromIntArray(arr);
        assertTrue(returned.isLeaf());
    }
    
    @Test
    public void testIsNotLeaf() {
        int[][] arr = 
        {{1, 3},
         {1, 1}};
       
        QuadTreeNode returned = QuadTreeNodeImpl.buildFromIntArray(arr);
        assertFalse(returned.isLeaf());
    }
    
    @Test
    public void testIsNotLeaf2() {
        int[][] arr = 
        {{1, 3, 1, 1},
         {1, 1, 1, 1},
         {1, 1, 1, 1},
         {1, 1, 1, 1}};
       
        QuadTreeNode returned = QuadTreeNodeImpl.buildFromIntArray(arr);
        assertFalse(returned.isLeaf());
    } 
    
    @Test
    public void testIsNotLeaf3() {
        int[][] arr = 
        {{1, 3, 1, 1},
         {1, 1, 1, 1},
         {1, 1, 1, 1},
         {1, 1, 1, 1}};
       
        QuadTreeNode returned = QuadTreeNodeImpl.buildFromIntArray(arr);
        QuadTreeNodeImpl nodeImpl = (QuadTreeNodeImpl) returned;
        assertFalse(nodeImpl.leftTop.isLeaf());
    }
    
    
    //test compression ratio
    @Test
    public void testCompressionRatio() {
        int[][] arr = 
            {{1, 1},
             {1, 1}};
        
        QuadTreeNode returned = QuadTreeNodeImpl.buildFromIntArray(arr);
        assertEquals(0.25, returned.getCompressionRatio(), 0.00001);
    }
    @Test
    public void testCompressionRatioLarge() {
        int[][] arr = 
            {{5, 5, 5, 5},
             {5, 5, 5, 5},
             {5, 5, 6, 6},
             {5, 5, 6, 6}};

        QuadTreeNode returned = QuadTreeNodeImpl.buildFromIntArray(arr);

        assertEquals(0.3125, returned.getCompressionRatio(), 0.00001);
    }
    
    //test decompression
    @Test
    public void testDecompressLarge() {
        int[][] arr1 = 
            {{5, 5, 4, 4},
             {5, 5, 4, 4},
             {8, 8, 6, 6},
             {8, 8, 6, 6}};
    
        QuadTreeNode returned = QuadTreeNodeImpl.buildFromIntArray(arr1);
        
        int[][] result = returned.decompress();
        
        
        assertArrayEquals(result, arr1);
    }
    
    //test decompression
    @Test
    public void testDecompressLarge2() {
        int[][] arr1 = 
            {{5, 5, 4, 4},
             {5, 5, 4, 4},
             {8, 8, 6, 6},
             {8, 8, 8, 6}};
    
        QuadTreeNode returned = QuadTreeNodeImpl.buildFromIntArray(arr1);
        
        int[][] result = returned.decompress(); 
        assertArrayEquals(arr1, result);
    }
    
    @Test
    public void testDecompressMegaLarge() {
        int[][] arr1 = 
            {{5, 5, 5, 5, 6, 6, 6, 6},
             {5, 5, 5, 5, 6, 6, 6, 6},
             {5, 5, 5, 5, 6, 6, 6, 6},
             {5, 5, 5, 5, 6, 6, 6, 6},
             {4, 4, 4, 4, 0, 0, 5, 5},
             {4, 4, 4, 4, 0, 0, 5, 5},
             {4, 4, 4, 4, 5, 5, 5, 5},
             {4, 4, 4, 4, 5, 5, 5, 5}};
    
        QuadTreeNode returned = QuadTreeNodeImpl.buildFromIntArray(arr1);
        
        int[][] result = returned.decompress();
      
        assertArrayEquals(result, arr1);
    }
    
    @Test
    public void decompress4x4() {
        int[][] arr1 = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}};
        
        QuadTreeNode returned = QuadTreeNodeImpl.buildFromIntArray(arr1);
        
        int[][] result = returned.decompress();
        assertArrayEquals(result, arr1);
    }

    
    @Test
    public void testDecompressIsLeaf() {
        int[][] arr1 = 
            {{5, 5, 5, 5},
             {5, 5, 5, 5},
             {5, 5, 5, 5},
             {5, 5, 5, 5}};
    
        QuadTreeNode returned = QuadTreeNodeImpl.buildFromIntArray(arr1);
        
        int[][] result = returned.decompress();
        
        assertArrayEquals(result, arr1);
    }
    
    @Test
    public void f8x8() {
        int[][] testArray = {{-1, -2, 3, 4, -2, -6, -8, -8},
                             {-2, -2, 8, 8, -3, -9, -9, -9},
                             {1, 1, 3, 3, -7, -9, -9, -8},
                             {1, 1, 3, 3, -7, -9, -9, -9},
                             {0, 0, 0, 0, 6, 6, 6, 6},
                             {0, 8, 0, 0, 6, 6, 6, 6},
                             {0, 0, 0, 0, 6, 6, 6, 6},
                             {0, 0, 0, 0, 6, 6, 6, 0}};
        
        QuadTreeNode testNode = QuadTreeNodeImpl.buildFromIntArray(testArray);
        QuadTreeNodeImpl nodeImpl = (QuadTreeNodeImpl) testNode;
        assertNotNull(nodeImpl.leftTop);
        assertFalse(nodeImpl.leftTop.isLeaf());
        assertFalse(nodeImpl.leftBottom.isLeaf());
        assertFalse(nodeImpl.rightTop.isLeaf());
        assertFalse(nodeImpl.rightBottom.isLeaf());
    }
        
    @Test
    public void f2X2() {
        int[][] testArray = {{1, 2}, {3, 4}};
            
        QuadTreeNode testNode = QuadTreeNodeImpl.buildFromIntArray(testArray);
            
        QuadTreeNodeImpl nodeImpl = (QuadTreeNodeImpl) testNode;
          
        assertTrue(nodeImpl.leftTop.isLeaf());
        assertTrue(nodeImpl.leftBottom.isLeaf());
        assertTrue(nodeImpl.rightTop.isLeaf());
        assertTrue(nodeImpl.rightBottom.isLeaf());
        assertEquals(1, (int) nodeImpl.leftTop.color);
        assertEquals(2, (int) nodeImpl.rightTop.color);
        assertEquals(3, (int) nodeImpl.leftBottom.color);
        assertEquals(4, (int) nodeImpl.rightBottom.color);
    }
        
    @Test
    public void f4X4() {
        int[][] arr = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}};
            
        QuadTreeNode testNode = QuadTreeNodeImpl.buildFromIntArray(arr);
        QuadTreeNodeImpl nodeImpl = (QuadTreeNodeImpl) testNode;
        assertFalse(nodeImpl.leftTop.isLeaf());
        assertFalse(nodeImpl.leftBottom.isLeaf());
        assertFalse(nodeImpl.rightTop.isLeaf());
        assertFalse(nodeImpl.rightBottom.isLeaf());
    }
        
        //getColor test
    @Test
    public void getColor4x4() {
        int[][] arr1 = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}};
            
        QuadTreeNode testNode = QuadTreeNodeImpl.buildFromIntArray(arr1);
        QuadTreeNodeImpl nodeImpl = (QuadTreeNodeImpl) testNode;
        assertEquals(10, nodeImpl.getColor(1, 2));
    }
        
    @Test
     public void getColor8x8() {
        int[][] testArray = 
            {{-1, -2, 3, 4, -2, -6, -8, -8},
            {-2, -2, 8, 8, -3, -9, -9, -9},
            {1, 1, 3, 3, -7, -9, -9, -8},
            {1, 1, 3, 3, -7, -9, -9, -9},
            {0, 0, 0, 0, 6, 6, 6, 6},
            {0, 8, 0, 0, 6, 6, 6, 6},
            {0, 0, 0, 0, 6, 6, 6, 6},
            {0, 0, 0, 0, 6, 6, 6, 0}};
            
        QuadTreeNode testNode = QuadTreeNodeImpl.buildFromIntArray(testArray);
        QuadTreeNodeImpl nodeImpl = (QuadTreeNodeImpl) testNode;
        assertEquals(3, nodeImpl.getColor(2, 3));
        assertEquals(-1, nodeImpl.getColor(0, 0));
        assertEquals(6, nodeImpl.getColor(5, 5));
    }
        
    @Test
    public void getColor2x2() {
        int[][] testArray = {
                {1, 2},
                {5, 6}};
            
        QuadTreeNode testNode = QuadTreeNodeImpl.buildFromIntArray(testArray);
        QuadTreeNodeImpl nodeImpl = (QuadTreeNodeImpl) testNode;
        assertEquals(6, nodeImpl.getColor(1, 1));
    }
        
  
      //setColor tests
        
    @Test
    public void setColor2x2() {
        int[][] testArray = {
                {5, 2},
                {5, 6}};
            
        QuadTreeNode testNode = QuadTreeNodeImpl.buildFromIntArray(testArray);
        QuadTreeNodeImpl nodeImpl = (QuadTreeNodeImpl) testNode;
        nodeImpl.setColor(0, 0, 1);
        assertEquals(1, nodeImpl.getColor(0, 0));
    }
        
    @Test
     public void setColor4x4() {
        int[][] testArray = {
            {1, 1, 3, 4},
            {1, 1, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 16}};
            
        QuadTreeNode testNode = QuadTreeNodeImpl.buildFromIntArray(testArray);
        QuadTreeNodeImpl nodeImpl = (QuadTreeNodeImpl) testNode;
        nodeImpl.setColor(0, 0, 3);
        assertEquals(3, nodeImpl.getColor(0, 0));
    }
        
    @Test
     public void setColor2x2EdgeCase() {
        int[][] testArray = {
            {5, 5},
            {5, 5}};
            
        QuadTreeNode testNode = QuadTreeNodeImpl.buildFromIntArray(testArray);
        QuadTreeNodeImpl nodeImpl = (QuadTreeNodeImpl) testNode;
        nodeImpl.setColor(1, 1, 3);
        assertEquals(3, nodeImpl.getColor(1, 1));
    }
        
    @Test
     public void setColor2x2EdgeCase2() {
        int[][] testArray = {
            {5, 5},
            {5, 1}};
            
        QuadTreeNode testNode = QuadTreeNodeImpl.buildFromIntArray(testArray);
        QuadTreeNodeImpl nodeImpl = (QuadTreeNodeImpl) testNode;
        nodeImpl.setColor(1, 1, 5);
        assertTrue(nodeImpl.isLeaf());
        assertEquals(5, nodeImpl.getColor(1, 1));
    }
        
     //getQuadrant test
    @Test 
     public void testGetQuadrant() {
        int[][] arr = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}};
          
        QuadTreeNode testNode = QuadTreeNodeImpl.buildFromIntArray(arr);
        QuadTreeNode result = testNode.getQuadrant(QuadTreeNode.QuadName.BOTTOM_LEFT);
        QuadTreeNodeImpl nodeImpl = (QuadTreeNodeImpl) testNode;
        assertEquals(result, nodeImpl.leftBottom);
    }

}
