/*
 * Copyright (c) 2018 superblaubeere27
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package uwu.smsgamer.Paste.valuesystem;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValueManager {

    private HashMap<String, List<Value>> valueMap = new HashMap<>();

    /**
     * @param name   The name of the owner
     * @param object The object where value-fields are declared
     */
    public void registerObject(String name, Object object) {
        List<Value> values = new ArrayList<>();
        for (final Field field : object.getClass().getDeclaredFields()) {
            try {
                field.setAccessible(true);
                final Object obj = field.get(object);

                if (obj instanceof Value) {
                    values.add((Value) obj);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        valueMap.put(name, values);
    }

    /**
     * @param name The name of the owner
     * @return If there's an owner with this name (the case is ignored) it will return all values of it else it will return null
     */

    public List<Value> getAllValuesFrom(String name) {
        for (Map.Entry<String, List<Value>> stringListEntry : valueMap.entrySet()) {
            if (stringListEntry.getKey().equalsIgnoreCase(name)) return stringListEntry.getValue();
        }
        return null;
    }


    public HashMap<String, List<Value>> getAllValues() {
        return valueMap;
    }


    /**
     * @param owner The name of the owner
     * @param name  The name of the value
     * @return The value or null
     */

    public Value get(String owner, String name) {
        List<Value> found = getAllValuesFrom(owner);

        if (found == null) return null;

        return found.stream().filter(val -> name.equalsIgnoreCase(val.getName())).findFirst().orElse(null);
    }

}
