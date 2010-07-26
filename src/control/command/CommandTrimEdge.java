package control.command;

import control.command.exceptions.CommandExecutionException;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Esteban I. Invernizzi (invernizzie@gmail.com)
 *         Date: 07/07/2010
 */
public class CommandTrimEdge extends MyFrameCommand {

    @Override
    protected void doExecute() throws CommandExecutionException {

        getFrame().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                if (getFrame().getProfile() != null)
                    getFrame().getProfile().trimRightmostEndsHorizontally(event.getX() - getFrame().getLeftOffset());
                getFrame().removeMouseListener(this);
                getFrame().repaint();
            }
        });
    }
}
