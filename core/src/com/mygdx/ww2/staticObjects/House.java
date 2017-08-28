package com.mygdx.ww2.staticObjects;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.ww2.Main;
import com.mygdx.ww2.abstracts.ScreenObject;
import com.mygdx.ww2.dinamicObjects.Worker;
import com.mygdx.ww2.screens.GameScreen;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Gagiu Filip on 8/21/2017.
 */
public class House extends ScreenObject {

    public final GameScreen instance;
    public Vector2 position;
    //the workers of this house
    private Queue<Worker> workersOnThis = new ConcurrentLinkedQueue<Worker>();
    public float money;
    public boolean wastedMoney;

    // the instace of a house where workers can farm money
    public House(GameScreen getInstance) {
        position = new Vector2();
        // get the world of this house
        instance = getInstance;
        money = Main.reference.constants.resources;

        //------Test Purpose--------//
        position.set(100, instance.platform.platformParallax.texture.getHeight());
        //-------------------------//
    }


    @Override
    public void draw() {
        Main.reference.batch.begin();
        Main.reference.batch.draw(Main.reference.registry.retrieveTextures(1), position.x, position.y);
        Main.reference.batch.end();

    }

    @Override
    public void update() {
        for (ScreenObject worker : instance.spawnedObjects) {
            try {
                //if the worker is in this instance of house farm money
                //if is posible to add a worker to this house add a new worker
                Worker thisWorker = (Worker) (worker);
                if (!thisWorker.binded) {
                    bindToHouse(thisWorker);
                    if (workersOnThis.contains(thisWorker))
                        unBind(thisWorker);
                }
                if (workersOnThis.contains(thisWorker)) thisWorker.farmMoney(this);
            } catch (Exception ignore) {
                // not all screenObjects are worker so ignore
            }
        }
        wastedMoney = money < Main.reference.constants.nullified;
    }

    private void bindToHouse(Worker worker) {
        if (workersOnThis.size() < Main.reference.constants.maxWorkers) {
            workersOnThis.add(worker);
            worker.bind();
        }
    }

    private void unBind(Worker worker) {
        // when there is nothing left to extract remove this worker
        if (money < Main.reference.constants.nullified) {
            workersOnThis.remove(worker);
            worker.unbind();
        }
    }
}
