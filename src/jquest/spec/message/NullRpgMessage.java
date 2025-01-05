package jquest.spec.message;

import java.util.Collections;
import java.util.List;

class NullRpgMessage implements RpgMessage {

  @Override
  public List<RpgMessageLine> lines() {
    return Collections.emptyList();
  }

  @Override
  public boolean isEmpty() {
    return true;
  }
}
