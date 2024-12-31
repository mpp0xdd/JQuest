package jquest.spec.message;

import java.util.Collections;
import java.util.List;

class NullRpgMessage implements RpgMessage {

  public NullRpgMessage() {}

  @Override
  public List<String> lines() {
    return Collections.emptyList();
  }
}
