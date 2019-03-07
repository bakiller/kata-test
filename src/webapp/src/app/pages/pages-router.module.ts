import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {ScoreComponent} from "./score/score.component";
import {HttpClientModule} from "@angular/common/http";


const routes: Routes = [{path: '', component: ScoreComponent}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PagesRouterModule {
}
