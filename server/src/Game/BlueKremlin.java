package Game;
/**
Clase hija de cocodrilo para crear enemigos azules
*/
public class BlueKremlin extends Kremlin
{
    /**
    * Constructor de Azul
    * @param x_position posicion en x
    * @param y_position posicion en y
    * @param liana_position datos de la liana
    */
    public BlueKremlin(Integer x_position, Integer y_position, Integer[] liana_position)
    {
        
        this.vine = liana_position;
        this.x = x_position;
        this.y = y_position;
        this.position = "2," + x_position + "," + y_position;

    }
    /*
    * Función para mover a un enemigo azul a lo largo de la liana
    */
    @Override
    public void move()
    {
        this.y = this.y + speed;
        this.position = "2," + this.x + "," + this.y;
    }
    

}
