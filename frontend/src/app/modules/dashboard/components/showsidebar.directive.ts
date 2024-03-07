import {
  Directive,
  ElementRef,
  Input,
  Renderer2,
  SimpleChanges,
} from '@angular/core';

@Directive({
  selector: '[show-sidebar]',
})
export class ShowsidebarDirective {
  @Input() isShowSidebar = false;

  constructor(private el: ElementRef, private render: Renderer2) {}

  ngOnChanges(changes: SimpleChanges) {
    if (changes['isShowSidebar']) {
      this.isShowSidebar ? this.showSidebar() : this.hiddeSidebar();
    }
  }

  showSidebar() {
    this.render.setStyle(this.el.nativeElement, 'left', "0");
  }

  hiddeSidebar() {
    this.render.setStyle(this.el.nativeElement, 'left', "-100%");
  }
}
