import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ScoreComponent} from './score/score.component';
import {MatButtonModule, MatCardModule, MatSidenavModule, MatTabsModule, MatToolbarModule} from '@angular/material';
import {PagesRouterModule} from "./pages-router.module";

@NgModule({
  imports: [
    CommonModule,
    PagesRouterModule,
    MatToolbarModule,
    MatSidenavModule,
    MatTabsModule,
    MatCardModule,
    MatButtonModule
  ],
  declarations: [ScoreComponent]
})
export class PagesModule {
}
