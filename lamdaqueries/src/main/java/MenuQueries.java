
import java.util.List;
import static java.util.stream.Collectors.toList;

public class MenuQueries
{
    public List<String> getDishNamesForArgCalories (List<Dish> menu, int calories)
    {
        return menu.stream().filter(dish -> dish.getCalories() > calories).map(Dish::getName).collect(toList());
    }

}
