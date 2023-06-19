package Game;
/**
Clase hija de cocodrilo para crear enemigos rojos
*/
public class RedKremlin extends Kremlin {
    private Boolean down;
    /**
    * Constructor de rojo
    * @param x_position posicion en x
    * @param y_position posicion en y
    * @param vine_position datos de la liana
    */
    public RedKremlin(Integer x_position, Integer y_position, Integer[] vine_position)
    {
        
        this.down = true;
        this.vine = vine_position;
        this.x = x_position;
        this.y = y_position;
        this.position = "1," + x_position + "," + y_position;

    }
    /*
    * Función para mover a un enemigo rojo a lo largo de la liana
    */
    @Override
    public void move()
    {
        if(y<=(vine[1] + 20))
        {
            down = true;
        }
        if(y>=(vine[2] - 20))
        {
            down = false;
        }
        if(down)
        {
            move_down();
        }
        else
        {
            move_up();
        }
        
    }
    /*
    * Función para mover a un enemigo rojo hacia arriba
    */
    private void move_down()
    {
        this.y = this.y + speed;
        this.position = "1," + this.x + "," + this.y;
    }
    /*
    * Función para mover a un enemigo rojo hacia abajo
    */
    private void move_up()
    {
        this.y = this.y - speed;
        this.position = "1," + this.x + "," + this.y;
    }
}
