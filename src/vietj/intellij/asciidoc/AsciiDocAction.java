/*
 * Copyright 2013 Julien Viet
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package vietj.intellij.asciidoc;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataKeys;
import com.intellij.psi.PsiFile;
import vietj.intellij.asciidoc.file.AsciiDocFileType;

/** @author Julien Viet */
public class AsciiDocAction extends AnAction {
  public void actionPerformed(AnActionEvent event) {
    PsiFile file = event.getData(DataKeys.PSI_FILE);
    new AsciiDoc().render(file.getText());
  }

  @Override
  public void update(AnActionEvent e) {
    PsiFile file = e.getData(DataKeys.PSI_FILE);
    boolean enabled = false;
    if (file != null) {
      for (String ext: AsciiDocFileType.DEFAULT_ASSOCIATED_EXTENSIONS) {
        if (file.getName().endsWith("." + ext)) {
          enabled = true;
          break;
        }
      }
    }
    e.getPresentation().setEnabled(enabled);
  }
}
