package control.command;

import control.command.exceptions.CommandExecutionException;
import model.edgedetection.Stroke;
import model.edgedetection.impl.ProximityPointStroker;
import model.edgedetection.impl.ScanningPointStroker;
import model.edgedetection.impl.WidthCorrectingStrokeDetector;

/**
 * @author Esteban I. Invernizzi (invernizzie@gmail.com)
 * @created 09/05/2010
 */
public class CommandDetectEdges extends MyFrameCommand {

    protected void doExecute() throws CommandExecutionException {
        getFrame().setStrokes(
                new WidthCorrectingStrokeDetector(getFrame().getImage(), new ProximityPointStroker())
                .generateStrokes());
        Stroke profile = Stroke.determineLongest(getFrame().getStrokes());
        //getFrame().getStrokes().remove(profile);
        //profile.mirror();
        getFrame().setProfile(profile);
        getFrame().repaint();
    }

    protected void testExecute() throws CommandExecutionException {
        super.testExecute();
        if (getFrame().getImage() == null)
            throw new CommandExecutionException(this);
    }
}
