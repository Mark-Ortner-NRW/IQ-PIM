import { Component, OnInit } from '@angular/core';

import { IBrand } from 'app/shared/model/brand.model';
import { BrandService } from 'app/entities/brand/brand.service';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

@Component({
  selector: 'jhi-page-two',
  templateUrl: './page-two.component.html',
  styleUrls: ['page-two.component.scss'],
})
export class PageTwoComponent implements OnInit {
  cols: any[] = [
    { field: 'id', header: 'ID' },
    { field: 'name', header: 'Name' },
    { field: 'description', header: 'Description' },
    { field: 'createdAt', header: 'Created At' },
  ];
  brands?: IBrand[] | any;

  constructor(private brandService: BrandService) {}

  ngOnInit(): void {
    this.brandService.query({}).subscribe(
      (res: HttpResponse<IBrand[]>) => this.onSuccess(res.body),
      () => this.onError()
    );
  }
  protected onSuccess(data: IBrand[] | null): void {
    this.brands = data || [];
  }
  protected onError(): void {}
}
