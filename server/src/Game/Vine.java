package Game;
/*
* Clase para crear una Liana
*/
public class Vine
{
    private Integer id;
    private Integer x;
    private Integer y_beginning;
    private Integer y_end;
    /**
    * Constructor de Liana
    * @param ID id de la liana
    * @param X posición en x de la liana
    * @param y_beginning inicio de la liana en y
    * @param y_end final de la liana en y
    */
    public Vine(Integer ID, Integer X, Integer y_beginning, Integer y_end )
    {
        
        this.x = X;
        this.y_beginning = y_beginning;
        this.y_end = y_end;

        this.id = ID;
    }
    /**
    * Función para obtener el ID de la liana
    * @return id
    */
    public Integer getId()
    {
        return id;
    }
    /**
    * Función para obtener el valor de la posicion de la liana
    * @return posicion [x, y_inicio, y_fin]
    */
    public Integer[] getPosicion()
    {
        Integer posicion[] = new Integer[3];
        posicion[0] = this.x;
        posicion[1] = this.y_beginning;
        posicion[2] = this.y_end;
        return posicion;
    }

    
}
