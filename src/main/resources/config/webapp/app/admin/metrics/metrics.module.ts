import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { InnoqPimSharedModule } from '../../shared/shared.module';

import { MetricsComponent } from './metrics.component';

import { metricsRoute } from './metrics.route';

@NgModule({
  imports: [InnoqPimSharedModule, RouterModule.forChild([metricsRoute])],
  declarations: [MetricsComponent],
})
export class MetricsModule {}
