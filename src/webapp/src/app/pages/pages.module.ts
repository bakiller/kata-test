import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ScoreComponent } from './score/score.component';
import {PagesRouterModule} from "./pages-router.module";

@NgModule({
  imports: [
    CommonModule,
    PagesRouterModule
  ],
  declarations: [ScoreComponent]
})
export class PagesModule { }
