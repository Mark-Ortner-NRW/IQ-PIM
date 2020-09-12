import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { InnoqPimSharedModule } from '../shared/shared.module';
import { HOME_ROUTE } from './home.route';
import { HomeComponent } from './home.component';

@NgModule({
  imports: [InnoqPimSharedModule, RouterModule.forChild([HOME_ROUTE])],
  declarations: [HomeComponent],
})
export class InnoqPimHomeModule {}
