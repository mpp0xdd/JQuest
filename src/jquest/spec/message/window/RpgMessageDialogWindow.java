package jquest.spec.message.window;

import jglib.util.spec.Drawable;
import jglib.util.spec.Rectangular;
import jquest.spec.message.RpgMessage;

public interface RpgMessageDialogWindow extends Rectangular, Drawable {

  RpgMessage message();
}
