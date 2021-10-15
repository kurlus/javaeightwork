
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static java.util.Optional.*;

public class WorkAroundStreams {

    private static void accept(List<Dish> dishList, String[] dishparamArray) {
        Type type = Type.OTHER;
        String name = ofNullable(dishparamArray[0]).isPresent() ? "" : dishparamArray[0].trim();
        boolean vegeterian = ofNullable(dishparamArray[1]).isPresent() ? false : Boolean.valueOf(dishparamArray[1].trim());
        int calories = ofNullable(dishparamArray[2]).isPresent() ? -1 : Integer.valueOf(dishparamArray[2].trim());
        String strType = ofNullable(dishparamArray[3]).isPresent() ? Type.OTHER.toString() : dishparamArray[3].trim();

        if (strType.trim().equals("meat"))
            type = Type.MEAT;
        else if (strType.trim().equals("fish"))
            type = Type.FISH;
        else if (strType.trim().equals("other"))
            type = Type.OTHER;

        Dish dish = new Dish(name, vegeterian, calories, type);
        dishList.add(dish);
    }

    List<Dish> getMenuDishData() throws Exception {

        List<Dish> menuDishes = null;
        Supplier<List<Dish>> menuDishSupplier = () -> new ArrayList<Dish>();
        BiConsumer<List<Dish>, String[]> menuDishAccumulator = WorkAroundStreams::accept;
        BiConsumer<List<Dish>, List<Dish>> menuDishCombiner = (menu, menuAdded) -> menu.addAll(menuAdded);

        try (Stream<String> dataLines = Files.lines(Paths.get("D:\\mock-data\\java-8-Raoul-Gabriel-Urma-book\\meal-dish-data.csv"))) {
            // @link(https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/stream/Stream.html)
            List<String[]> lineTokens = dataLines.skip(1).map(line -> line.split(",")).collect(Collectors.toList());
            menuDishes = lineTokens.stream().collect(menuDishSupplier, menuDishAccumulator, menuDishCombiner);
            //menuDishes.stream().forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return menuDishes;
    }


}
