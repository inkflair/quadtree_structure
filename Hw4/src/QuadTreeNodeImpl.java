// CIS 121, HW4 QuadTree

public class QuadTreeNodeImpl implements QuadTreeNode {
    /**
     * ! Do not delete this method !
     * Please implement your logic inside this method without modifying the signature
     * of this method, or else your code won't compile.
     * <p/>
     * Be careful that if you were to create another method, make sure it is not public.
     *
     * @param image image to put into the tree
     * @return the newly build QuadTreeNode instance which stores the compressed image
     * @throws IllegalArgumentException if image is null
     * @throws IllegalArgumentException if image is empty
     * @throws IllegalArgumentException if image.length is not a power of 2
     * @throws IllegalArgumentException if image, the 2d-array, is not a perfect square
     */
    
    //fields - color, QuadTreeNode x4
    QuadTreeNodeImpl leftTop;
    QuadTreeNodeImpl leftBottom;
    QuadTreeNodeImpl rightTop;
    QuadTreeNodeImpl rightBottom;
    Integer color; 
    int dimension;
    //leaf = same color
    
    //what does PACKAGE PRIVATE MEAN?
    
    //constructor
    public QuadTreeNodeImpl(QuadTreeNodeImpl leftTop, QuadTreeNodeImpl leftBottom, 
            QuadTreeNodeImpl rightTop,  
            QuadTreeNodeImpl rightBottom, Integer color, int dimension) {
        this.leftTop = leftTop;
        this.leftBottom = leftBottom; 
        this.rightTop = rightTop;
        this.rightBottom = rightBottom;
        this.color = color;
        this.dimension = dimension;
    }
    
    //x and y is always at bottom right corner
    
    private static QuadTreeNodeImpl helperFunctionBuilt(int[][] image, int length, int y, int x) {
        
       
        //leaf base case -- subtract from x or why of length 
        if (length == 1) {
            return new QuadTreeNodeImpl(null, null, null, null, image[y - 1][x - 1], 1);
        }
        
        //left top
        QuadTreeNodeImpl topLeft = helperFunctionBuilt(image, length / 2, y - length / 2, 
                x - length / 2);

        //left bottom
        QuadTreeNodeImpl bottomLeft = helperFunctionBuilt(image, length / 2, y, x - length / 2);
        
        //right top
        QuadTreeNodeImpl topRight = helperFunctionBuilt(image, length / 2, y - length / 2, x);

        //right bottom
        QuadTreeNodeImpl bottomRight = helperFunctionBuilt(image, length / 2, y, x);

        //base case - leaf's are the same
        if (topLeft.color != null && topLeft.color == bottomLeft.color && 
                topLeft.color == topRight.color && 
                topLeft.color == bottomRight.color) {
            return new QuadTreeNodeImpl(null, null, null, null, 
                    topLeft.color, 2 * topLeft.dimension);
        } else {
            return new QuadTreeNodeImpl(topLeft, bottomLeft, topRight, bottomRight,
                    null, 2 * topLeft.dimension);
        }
        //if leaf -- everything within the leaf is a single color
        //base case -- no longer can be divide by 4 
    }
    
    
    private static boolean isPowerOf2(int length) {
        if (length == 1) {
            return true;
        }
        if (length % 2 != 0 || length == 0) {
            return false;
        } else {
          
            for (int i = 0; i <= length; i++) {
                if (Math.pow(2, i) == length) {
                    return true;
                }
            }
        }
        return false;
    }
      
    
    public static QuadTreeNode buildFromIntArray(int[][] image) {
        
        if (image == null) {
            throw new IllegalArgumentException("Image is null");
        }
        
        //check for power of 2
        if (image.length == 0 || !isPowerOf2(image.length) && !isPowerOf2(image[0].length)) {
            throw new IllegalArgumentException("Array either empty or not power of 2");
        }
        
        for (int i = 0; i < image.length; i++) {
            if (image.length != image[i].length) {
                throw new IllegalArgumentException("Image not perfect square");
            }
        }
        ///potential helper
        return helperFunctionBuilt(image, image.length, image.length, image.length);
    }

    @Override
    public int getColor(int x, int y) {
        
        if (x > this.dimension || y > this.dimension || x < 0 || y < 0) {
            throw new IllegalArgumentException("OOB");
        }
 
        int halfDim = this.dimension / 2; //this will be 2
        
        if (this.isLeaf()) {
            return this.color;
        }
        //top or bottom quadrant
        if (x < halfDim && y < halfDim) { //know it's top left
            return this.leftTop.getColor(x, y);
   
        } else if (x < halfDim && y >= halfDim) { //know it's left Bottom
            return this.leftBottom.getColor(x, y - this.leftBottom.dimension); // or /2
            
        } else if (x >= halfDim && y < halfDim) { //know it's top right
            return this.rightTop.getColor(x - this.rightTop.dimension, y);
            
        } else { //bottom right
            return this.rightBottom.getColor(x - this.rightBottom.dimension, 
                    y - this.rightBottom.dimension);
        }
    }

    @Override
    public void setColor(int x, int y, int c) {
        
        if (x > this.dimension || y > this.dimension || x < 0 || y < 0) {
            throw new IllegalArgumentException("OOB");
        }
        int halfDim = this.dimension / 2; //this will be 2
        
        if (this.isLeaf() && this.dimension == 1) {
            this.color = c;
            return;  
        } 
        
        if (this.isLeaf() && this.dimension != 1) {
            
            
            
            //CASE: null --> having childrenNodes
            QuadTreeNodeImpl topLeft =  new QuadTreeNodeImpl(null, null, null, null,
                    this.color, this.dimension / 2);

            //left bottom
            QuadTreeNodeImpl bottomLeft =  new QuadTreeNodeImpl(null, null, null, null, 
                    this.color, this.dimension / 2);

            //right top
            QuadTreeNodeImpl topRight =  new QuadTreeNodeImpl(null, null, null, null, 
                    this.color, this.dimension / 2);


            //right bottom
            QuadTreeNodeImpl bottomRight = new QuadTreeNodeImpl(null, null, null, null, 
                    this.color, this.dimension / 2);

            this.color = null;
            this.leftBottom = bottomLeft;
            this.leftTop = topLeft;
            this.rightBottom = bottomRight;
            this.rightTop = topRight;
        }
        
        //top or bottom quadrant
        if (x < halfDim && y < halfDim) { //know it's top left
            this.leftTop.setColor(x, y, c);
   
        } else if (x < halfDim && y >= halfDim) { //know it's left Bottom
            this.leftBottom.setColor(x, y - this.leftBottom.dimension, c); // or /2
            
        } else if (x >= halfDim && y < halfDim) {
            this.rightTop.setColor(x - this.rightTop.dimension, y, c);
            
        } else { //bottom right
            this.rightBottom.setColor(x - this.rightBottom.dimension, 
                    y - this.rightBottom.dimension, c);
        } 
        
        
        //CASE: kill the childrenNodes
        if (this.leftTop.color != null && this.leftTop.color == 
                this.leftBottom.color && this.leftTop.color == this.rightTop.color && 
                this.leftTop.color == this.rightBottom.color) {
            
            this.dimension =  2 * this.leftTop.dimension;
            this.color = this.leftBottom.color;
            this.leftBottom = null;
            this.leftTop = null;
            this.rightBottom = null;
            this.rightTop = null;
        }
        
    }

    @Override
    public QuadTreeNode getQuadrant(QuadName quadrant) {
        switch (quadrant) {
            case BOTTOM_LEFT: 
                return this.leftBottom;
            case BOTTOM_RIGHT:
                return this.rightBottom;
            case TOP_LEFT:
                return this.leftTop;
            case TOP_RIGHT:
                return this.rightTop;
            default: return null;
        }
    }

    @Override
    public int getDimension() {
        return this.dimension;
    }

    @Override
    public int getSize() {
        if (this.leftBottom == null && this.rightBottom == null && this.rightTop == null
                && this.leftTop == null) {
            return 1;
        }
        return 1 + this.leftTop.getSize() + this.rightTop.getSize() + 
                this.leftBottom.getSize() + this.rightBottom.getSize();
    }

    @Override
    public boolean isLeaf() {

        return (this.leftBottom == null && this.rightBottom == null && this.rightTop == null
                && this.leftTop == null);
    }
    
    
    private static void decompressHelper(int[][] arr, 
            QuadTreeNodeImpl node, int x, int y) {

        if (node.leftTop.isLeaf()) {
            for (int i = y; i < node.leftTop.dimension + y; i++) {
                for (int j = x; j < node.leftTop.dimension + x; j++) {
                    arr[i][j] = node.leftTop.color;
                }
            }
        } else {
            decompressHelper(arr, node.leftTop, x, y);
        }
        
        if (node.leftBottom.isLeaf()) {
            for (int i = y + node.leftTop.dimension; i < y + node.leftTop.dimension + 
                    node.leftTop.dimension; i++) {
                for (int j = x; j < node.leftTop.dimension + x; j++) {
                    arr[i][j] = node.leftBottom.color;
                }
            }
        } else {
            decompressHelper(arr, node.leftBottom, x, y + node.leftTop.dimension);
        }
        
        if (node.rightTop.isLeaf()) {
            for (int i = y; i < node.leftTop.dimension + y; i++) {
                for (int j = x + node.leftTop.dimension; j < x + node.leftTop.dimension + 
                        node.leftTop.dimension; j++) {
                    arr[i][j] = node.rightTop.color;
                    
                }
            }
        } else {
            decompressHelper(arr, node.rightTop, x + node.leftTop.dimension, y);
        }
        
        if (node.rightBottom.isLeaf()) {
            for (int i = y + node.leftTop.dimension; i < y + node.leftTop.dimension + 
                    node.leftTop.dimension; i++) {
                for (int j = x + node.leftTop.dimension; j < x + node.leftTop.dimension + 
                        node.leftTop.dimension; j++) {
                    arr[i][j] = node.rightBottom.color;
                }
            }
        } else {
            decompressHelper(arr, node.rightBottom, x + node.leftTop.dimension, 
                    y + node.leftTop.dimension);
        }
    }
    
    @Override
    public int[][] decompress() {
        int size = this.dimension;
        int[][] arr = new int[size][size];
 
        if (this.isLeaf()) {
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[i].length; j++) {
                    arr[i][j] = this.color;
                }
            }
            return arr;
        }
        decompressHelper(arr, this, 0, 0);
        return arr;
    }

    @Override
    public double getCompressionRatio() {
        return (double) this.getSize() / (this.getDimension() * this.getDimension());
    }
}
