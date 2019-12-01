/*
 * Copyright (c) 2018 superblaubeere27
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package uwu.smsgamer.Paste.ClickGui;

import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import uwu.smsgamer.Paste.ClickGui.Components.Button;
import uwu.smsgamer.Paste.ClickGui.Components.Label;
import uwu.smsgamer.Paste.ClickGui.Components.*;
import uwu.smsgamer.Paste.ClickGui.Layout.GridLayout;
import uwu.smsgamer.Paste.Module.Category;
import uwu.smsgamer.Paste.Module.Module;
import uwu.smsgamer.Paste.PasteClient;
import uwu.smsgamer.Paste.Utils.FontRenderer.GlyphPageFontRenderer;
import uwu.smsgamer.Paste.Utils.MouseUtils;
import uwu.smsgamer.Paste.valuesystem.BooleanValue;
import uwu.smsgamer.Paste.valuesystem.ModeValue;
import uwu.smsgamer.Paste.valuesystem.NumberValue;
import uwu.smsgamer.Paste.valuesystem.Value;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClickGUI extends GuiScreen {
    private final GlyphPageFontRenderer consolas;
    private final Pane spoilerPane;
    private final HashMap<Category, Pane> categoryPaneMap;
    private Window window;
    private IRenderer renderer;
    private List<ActionEventListener> onRenderListeners = new ArrayList<>();

    public ClickGUI() {
        consolas = GlyphPageFontRenderer.create("Consolas", 15, false, false, false);
        renderer = new ClientBaseRendererImpl(consolas);

        window = new Window(PasteClient.clientName, 50, 50, 900, 400);

        Pane conentPane = new uwu.smsgamer.Paste.ClickGui.Components.ScrollPane(renderer, new uwu.smsgamer.Paste.ClickGui.Layout.GridLayout(1));

        Pane buttonPane = new Pane(renderer, new uwu.smsgamer.Paste.ClickGui.Layout.FlowLayout());

        HashMap<Category, List<Module>> moduleCategoryMap = new HashMap<>();
        categoryPaneMap = new HashMap<>();

        for (Module module : PasteClient.instance.moduleManager.getModules()) {
            if (!moduleCategoryMap.containsKey(module.getCategory())) {
                moduleCategoryMap.put(module.getCategory(), new ArrayList<>());
            }

            moduleCategoryMap.get(module.getCategory()).add(module);
        }

        HashMap<Category, Pane> paneMap = new HashMap<>();

        List<Spoiler> spoilers = new ArrayList<>();
        List<Pane> paneList = new ArrayList<>();

        for (Map.Entry<Category, List<Module>> moduleCategoryListEntry : moduleCategoryMap.entrySet()) {
            Pane spoilerPane = new Pane(renderer, new GridLayout(1));


            for (Module module : moduleCategoryListEntry.getValue()) {
                Pane settingPane = new Pane(renderer, new uwu.smsgamer.Paste.ClickGui.Layout.GridLayout(4));

                {
                    settingPane.addComponent(new Label(renderer, "State"));
                    CheckBox cb;
                    settingPane.addComponent(cb = new CheckBox(renderer, "Enabled"));
                    onRenderListeners.add(() -> cb.setSelected(module.isToggled()));
                    cb.setListener(val -> {
                        module.toggle();
                        return true;
                    });
                }
                {
                    settingPane.addComponent(new Label(renderer, "Keybind"));
                    KeybindButton kb;
                    settingPane.addComponent(kb = new KeybindButton(renderer, Keyboard::getKeyName));
                    onRenderListeners.add(() -> kb.setValue(module.getKey()));

                    kb.setListener(val -> {
                        module.setKey(val);
                        System.out.println();
                        return true;
                    });
                }

                List<Value>values = null;//PasteClient.instance.valueManager.getAllValuesFrom(module.getName())
                // FIXME: 2019-11-30 Make .getAllValuesFrom actually work.

                if (values != null) {
                    for (Value value : values) {
                        if (value instanceof BooleanValue) {
                            settingPane.addComponent(new Label(renderer, value.getName()));

                            CheckBox cb;

                            settingPane.addComponent(cb = new CheckBox(renderer, "Enabled"));
                            cb.setListener(value::setObject);
                            onRenderListeners.add(() -> cb.setSelected(((BooleanValue) value).getObject()));
                        }
                        if (value instanceof ModeValue) {
                            settingPane.addComponent(new Label(renderer, value.getName()));

                            ComboBox cb;

                            settingPane.addComponent(cb = new ComboBox(renderer, ((ModeValue) value).getModes(), ((ModeValue) value).getObject()));
                            cb.setListener(object -> {
                                value.setObject(object);

                                System.out.println("lol");
                                return true;
                            });
                            onRenderListeners.add(() -> cb.setSelectedIndex(((ModeValue) value).getObject()));
                        }
                        if (value instanceof NumberValue) {
                            settingPane.addComponent(new Label(renderer, value.getName()));

                            Slider cb;

                            Slider.NumberType type = Slider.NumberType.DECIMAL;

                            if (value.getObject() instanceof Integer) {
                                type = Slider.NumberType.INTEGER;
                            } else if (value.getObject() instanceof Long) {
                                type = Slider.NumberType.TIME;
                            } else if (value.getObject() instanceof Float && ((NumberValue) value).getMin().intValue() == 0 && ((NumberValue) value).getMax().intValue() == 100) {
                                type = Slider.NumberType.PERCENT;
                            }

                            settingPane.addComponent(cb = new Slider(renderer, ((Number) value.getObject()).doubleValue(), ((NumberValue) value).getMin().doubleValue(), ((NumberValue) value).getMax().doubleValue(), type));
                            cb.setListener(val -> {
                                if (value.getObject() instanceof Integer) {
                                    value.setObject(val.intValue());
                                }
                                if (value.getObject() instanceof Float) {
                                    value.setObject(val.floatValue());
                                }
                                if (value.getObject() instanceof Long) {
                                    value.setObject(val.longValue());
                                }
                                if (value.getObject() instanceof Double) {
                                    value.setObject(val.doubleValue());
                                }

                                return true;
                            });

                            onRenderListeners.add(() -> cb.setValue(((Number) value.getObject()).doubleValue()));
                        }
                    }
                }
                Spoiler spoiler = new Spoiler(renderer, module.getName(), width, settingPane);

                paneList.add(settingPane);
                spoilers.add(spoiler);

                spoilerPane.addComponent(spoiler);

                paneMap.put(moduleCategoryListEntry.getKey(), spoilerPane);
            }

            categoryPaneMap.put(moduleCategoryListEntry.getKey(), spoilerPane);


        }


        spoilerPane = new Pane(renderer, new GridLayout(1));


        for (Category moduleCategory : categoryPaneMap.keySet()) {
            Button button;
            buttonPane.addComponent(button = new uwu.smsgamer.Paste.ClickGui.Components.Button(renderer, moduleCategory.toString()));
            button.setOnClickListener(() -> setCurrentCategory(moduleCategory));
        }

        conentPane.addComponent(buttonPane);

        int maxWidth = Integer.MIN_VALUE;

        for (Pane pane : paneList) {
            maxWidth = Math.max(maxWidth, pane.getWidth());
        }

        window.setWidth(28 + maxWidth);

        for (Spoiler spoiler : spoilers) {
            spoiler.preferredWidth = maxWidth;
            spoiler.setWidth(maxWidth);
        }

        spoilerPane.setWidth(maxWidth);
        buttonPane.setWidth(maxWidth);

        conentPane.addComponent(spoilerPane);

        conentPane.updateLayout();

        window.setContentPane(conentPane);


        if (categoryPaneMap.keySet().size() > 0)
            setCurrentCategory(categoryPaneMap.keySet().iterator().next());
    }

    private void setCurrentCategory(Category category) {
        spoilerPane.clearComponents();
        spoilerPane.addComponent(categoryPaneMap.get(category));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        for (ActionEventListener onRenderListener : onRenderListeners) {
            onRenderListener.onActionEvent();
        }

        Point point = MouseUtils.calculateMouseLocation();
        window.mouseMoved(point.x * 2, point.y * 2);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glLineWidth(1.0f);

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        window.render(renderer);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_TEXTURE_2D);

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        window.mouseMoved(mouseX * 2, mouseY * 2);
        window.mousePressed(mouseButton, mouseX * 2, mouseY * 2);

        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        window.mouseMoved(mouseX * 2, mouseY * 2);
        window.mouseReleased(state, mouseX * 2, mouseY * 2);

        super.mouseReleased(mouseX, mouseY, state);
    }

    @Override
    protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
        window.mouseMoved(mouseX * 2, mouseY * 2);
        super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
    }

    @Override
    public void handleMouseInput() throws IOException {
        super.handleMouseInput();

        int eventDWheel = Mouse.getEventDWheel();

        window.mouseWheel(eventDWheel);

    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        window.keyPressed(keyCode, typedChar);
        super.keyTyped(typedChar, keyCode);
    }
}