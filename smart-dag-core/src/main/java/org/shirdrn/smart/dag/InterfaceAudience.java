package org.shirdrn.smart.dag;

import java.lang.annotation.Documented;

@InterfaceAudience.Public
public class InterfaceAudience {
	
  /**
   * Intended for use by any project or application.
   */
  @Documented public @interface Public {};
  
  /**
   * Intended only for the project(s) specified in the annotation.
   */
  @Documented public @interface LimitedPrivate {
    String[] value();
  };
  
  /**
   * Intended for use only within framework itself.
   */
  @Documented public @interface Private {};

  private InterfaceAudience() {} // Audience can't exist on its own
}
