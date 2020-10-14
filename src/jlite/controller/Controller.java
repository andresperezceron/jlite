package jlite.controller;

import jlite.model.Crud;
import jlite.model.ModelWithCrud;
import jlite.model.Model;
import jlite.view.Alle;
import jlite.view.EventsOnlyWiew;

import javax.swing.*;
import java.awt.event.ActionListener;

public abstract class Controller {
    public static final int ACTION_INDEX  = 1;
    public static final int ACTION_CREATE = 2;
    public static final int ACTION_UPDATE = 3;
    public static final int ACTION_DELETE = 4;
    public static final int ACTION_RENDER = 5;

    protected Alle e;
    protected Crud crud;

    private final JDialog view;
    private final Model model;

    public Controller(JDialog view, Model model) {
        this.view = view;
        this.model = model;
        if(model instanceof ModelWithCrud) crud = ((ModelWithCrud) model).getCrudModel();
        if(view instanceof EventsOnlyWiew) e = ((EventsOnlyWiew) view).getAlle();
    }

    public void executeAction(int ACTION_CONTROLLER) {
        switch(ACTION_CONTROLLER) {
            case ACTION_INDEX  :  index(); break;
            case ACTION_CREATE : create(); break;
            case ACTION_UPDATE : update(); break;
            case ACTION_DELETE : delete(); break;
            case ACTION_RENDER : render(); break;
        }
    }

    public JDialog getView() {
        return view;
    }
    public Model getModel() { return model; }

    protected abstract void  index();
    protected abstract void create();
    protected abstract void update();
    protected abstract void delete();
    protected abstract void render();

    protected ActionListener eActionController(int ACTION_CONTROLLER) {
        return e -> executeAction(ACTION_CONTROLLER);
    }
}
